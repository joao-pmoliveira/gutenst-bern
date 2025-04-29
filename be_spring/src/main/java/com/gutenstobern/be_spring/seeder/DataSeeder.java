package com.gutenstobern.be_spring.seeder;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gutenstobern.be_spring.entity.Author;
import com.gutenstobern.be_spring.entity.Book;
import com.gutenstobern.be_spring.entity.Edition;
import com.gutenstobern.be_spring.entity.Language;
import com.gutenstobern.be_spring.entity.User;
import com.gutenstobern.be_spring.entity.UserRole;
import com.gutenstobern.be_spring.repository.AuthorRepository;
import com.gutenstobern.be_spring.repository.BookRepository;
import com.gutenstobern.be_spring.repository.ContributorRoleRepository;
import com.gutenstobern.be_spring.repository.EditionRepository;
import com.gutenstobern.be_spring.repository.LanguageRepository;
import com.gutenstobern.be_spring.repository.UserRepository;
import com.gutenstobern.be_spring.repository.UserRoleRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private EditionRepository editionRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContributorRoleRepository contributorRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() > 0)
            return;

        ClassPathResource resource = new ClassPathResource("pg-catalog.json");
        InputStream is = resource.getInputStream();
        ObjectMapper mapper = new ObjectMapper();

        editionRepository.deleteAll();
        bookRepository.deleteAll();
        userRepository.deleteAll();
        authorRepository.deleteAll();
        languageRepository.deleteAll();
        userRoleRepository.deleteAll();
        contributorRoleRepository.deleteAll();

        UserRole adminRole = userRoleRepository.save(new UserRole("admin"));
        UserRole userRole = userRoleRepository.save(new UserRole("user"));

        String password = "1234";
        userRepository.save(new User("bob", passwordEncoder.encode(password), userRole));
        userRepository.save(new User("alicia", passwordEncoder.encode(password), userRole));
        userRepository.save(new User("admin", passwordEncoder.encode(password), adminRole));

        List<BookSeedDTO> books = mapper.readValue(
                is,
                new TypeReference<List<BookSeedDTO>>() {
                });

        books.stream()
                .sorted((b1, b2) -> b1.getTitle().compareToIgnoreCase(b2.getTitle()))
                .forEach(book -> System.out.println(book.getTitle()));

        for (BookSeedDTO dto : books) {
            System.out.println("--> DTO: " + dto.title);

            // Language

            if (dto.getLanguage().size() > 1)
                System.out.println("Multiple languages found... selecting first");
            String isoCode = dto.getLanguage().getFirst();
            Language language = languageRepository.findByIsoCode(isoCode)
                    .orElseGet(() -> languageRepository.save(new Language(
                            isoCode, Locale.forLanguageTag(isoCode).getDisplayLanguage(Locale.ENGLISH))));

            // Edition
            String title = dto.getTitle();

            if (title == null || title.isBlank()) {
                System.out.println("\tSkipping: no title");
                continue;
            }

            Edition edition = null;
            Optional<Edition> byTitle = editionRepository.findByTitle(title);

            if (!byTitle.isEmpty()) {
                System.out.println("\tTitle already exists in DB!");
                continue;
            }

            // Authors
            Set<Author> authors = new HashSet<>();
            for (AuthorSeedDTO author : dto.getAuthors()) {
                authorRepository.findByName(author.getName())
                        .ifPresentOrElse(
                                auth -> {
                                    authors.add(auth);
                                },
                                () -> {
                                    authors.add(authorRepository.save(new Author(author.getName())));
                                });
            }

            Book savedBook = bookRepository.save(
                    new Book(
                            title,
                            authors,
                            null));

            edition = new Edition();
            edition.setTitle(title);
            edition.setBook(savedBook);
            edition.setPages(-1);
            edition.setCoverURL(dto.getFiles()
                    .stream()
                    .filter(file -> file.getFormat().contains("image/jpeg"))
                    .map(file -> file.getUrl())
                    .findFirst()
                    .orElse(null));
            edition.setLanguage(language);
            edition.setContributors(null);
            edition.setPublicationYear(LocalDate.parse(
                    dto.getIssued_at(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd")).getYear());
            edition.setProjectGutenbergLink(null);

            editionRepository.save(edition);

        }

    }
}
