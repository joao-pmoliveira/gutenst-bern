package com.gutenstobern.be_spring.seeder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.gutenstobern.be_spring.entity.Author;
import com.gutenstobern.be_spring.entity.Book;
import com.gutenstobern.be_spring.entity.ContributorRole;
import com.gutenstobern.be_spring.entity.Edition;
import com.gutenstobern.be_spring.entity.EditionContributor;
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
        editionRepository.deleteAll();
        bookRepository.deleteAll();
        userRepository.deleteAll();
        authorRepository.deleteAll();
        languageRepository.deleteAll();
        userRoleRepository.deleteAll();
        contributorRoleRepository.deleteAll();

        // Languages
        Language english = new Language("en", "english");
        Language spanish = new Language("es", "spanish");
        Language portuguese = new Language("pt", "portuguese");
        Language french = new Language("fr", "french");
        Language russian = new Language("ru", "russian");
        Language japanese = new Language("ja", "japanese");

        languageRepository.save(english);
        languageRepository.save(spanish);
        languageRepository.save(portuguese);
        languageRepository.save(french);
        languageRepository.save(russian);
        languageRepository.save(japanese);

        // User Roles

        UserRole user = new UserRole("user");
        UserRole admin = new UserRole("admin");

        userRoleRepository.save(user);
        userRoleRepository.save(admin);

        // Users
        String encodedPass = passwordEncoder.encode("1234");
        List<User> users = Arrays.asList(
                new User("bob", encodedPass, user),
                new User("alicia", encodedPass, user),
                new User("charlie", encodedPass, user),
                new User("daniela", encodedPass, user),
                new User("admin", encodedPass, admin));

        userRepository.saveAll(users);

        // Contributor Roles
        ContributorRole authorRole = new ContributorRole("author");
        ContributorRole translatorRole = new ContributorRole("translator");
        ContributorRole illustratorRole = new ContributorRole("illustrator");
        ContributorRole testRole = new ContributorRole("test");

        contributorRoleRepository.save(authorRole);
        contributorRoleRepository.save(translatorRole);
        contributorRoleRepository.save(illustratorRole);
        contributorRoleRepository.save(testRole);

        // Authors
        Author janeAusten = new Author("Jane Austen");
        Author arthurCDoyle = new Author("Arthur Conan Doyle");
        Author hermanMelville = new Author("Herman Melville");
        Author miguelCervantes = new Author("Miguel de Cervantes");
        Author sunTzu = new Author("Sun Tzu");
        Author oscarWilde = new Author("Oscar Wilde");
        Author victorHugo = new Author("Victor Hugo");
        Author franzKafka = new Author("Franz Kafka");
        Author leoTolstoy = new Author("Leo Tolstoy");
        Author charlesDickens = new Author("Charles Dickens");
        Author luisMontero = new Author("Luis Garcia Montero");
        Author dmitriIvanov = new Author("Dmitri Ivanov");
        Author claraMoreau = new Author("Clara Moreau");
        Author edithGrossman = new Author("Edith Grossman");

        authorRepository.save(janeAusten);
        authorRepository.save(arthurCDoyle);
        authorRepository.save(hermanMelville);
        authorRepository.save(miguelCervantes);
        authorRepository.save(sunTzu);
        authorRepository.save(oscarWilde);
        authorRepository.save(victorHugo);
        authorRepository.save(franzKafka);
        authorRepository.save(leoTolstoy);
        authorRepository.save(charlesDickens);
        authorRepository.save(luisMontero);
        authorRepository.save(dmitriIvanov);
        authorRepository.save(claraMoreau);
        authorRepository.save(edithGrossman);

        // Books
        List<Book> books;

        Book pap = new Book(
                "Pride and Prejudice",
                Set.of(janeAusten),
                Set.of(
                        new Edition(
                                "Pride and Prejudice", "Original English edition",
                                "https://www.gutenberg.org/ebooks/1342",
                                "https://www.gutenberg.org/cache/epub/1342/pg1342.cover.medium.jpg",
                                english, 1813, 432,
                                null),
                        new Edition(
                                "Orgullo y Prejuicio", "Spanish translation",
                                null, null,
                                spanish, 1996, 440,
                                Set.of(new EditionContributor(translatorRole, luisMontero)))));
        Book ash = new Book(
                "The Adventures of Sherlock Holmes",
                Set.of(arthurCDoyle),
                Set.of(
                        new Edition(
                                "The Adventures of Sherlock Holmes", "Original edition",
                                "https://www.gutenberg.org/ebooks/1661",
                                "https://www.gutenberg.org/cache/epub/1661/pg1661.cover.medium.jpg",
                                english, 1892, 307,
                                null)));
        Book mb = new Book(
                "Moby-Dick",
                Set.of(hermanMelville),
                Set.of(
                        new Edition(
                                "Moby-Dick", "Original edition",
                                "https://www.gutenberg.org/ebooks/2701",
                                "https://www.gutenberg.org/cache/epub/2701/pg2701.cover.medium.jpg",
                                english, 1851, 635,
                                null)));
        Book dq = new Book(
                "Don Quixote",
                Set.of(miguelCervantes),
                Set.of(
                        new Edition(
                                "Don Quixote", "English edition by Edith Grossman",
                                null, null,
                                english, 2003, 863,
                                Set.of(new EditionContributor(translatorRole, edithGrossman))),
                        new Edition(
                                "Don Quijote", "Spanish original",
                                null, null,
                                spanish, 1605, 900,
                                null)));
        Book aow = new Book(
                "The Art of War",
                Set.of(sunTzu),
                Set.of(
                        new Edition(
                                "The Art of War", "Translated by Lionel Giles",
                                null, null,
                                english, 1910, 74,
                                null),
                        new Edition(
                                "Искусство войны", "Russian edition",
                                null, null,
                                russian, 1971, 76,
                                Set.of(new EditionContributor(translatorRole, dmitriIvanov)))));
        Book pod = new Book(
                "The Picture of Dorian Gray",
                Set.of(oscarWilde),
                Set.of(
                        new Edition(
                                "Dorian Gray", "English edition",
                                null, null,
                                english, 1890, 254,
                                null),
                        new Edition(
                                "Dorian Gray (Illustrated)", "With Illustrations",
                                null, null,
                                english, 1900, 254,
                                Set.of(new EditionContributor(illustratorRole, claraMoreau)))));
        Book lm = new Book(
                "Les Misérables",
                Set.of(victorHugo),
                Set.of(
                        new Edition(
                                "Les Misérables", "Original French edition",
                                null, null,
                                french, 1862, 1463,
                                null)));
        Book tm = new Book(
                "The Metamorphosis",
                Set.of(franzKafka),
                Set.of(
                        new Edition(
                                "The Metamorphosis", "Original",
                                null, null,
                                english, 1915, 201,
                                null)));
        Book ak = new Book(
                "Anna Karenina",
                Set.of(leoTolstoy),
                Set.of(
                        new Edition(
                                "Anna Karenina",
                                "English by Garnett",
                                null, null,
                                english, 1901, 864,
                                null)));
        Book ttc = new Book(
                "A Tale of Two Cities",
                Set.of(charlesDickens),
                Set.of(
                        new Edition(
                                "A Tale of Two Cities",
                                "English edition",
                                null, null,
                                english, 1859, 489,
                                null)));

        books = Arrays.asList(pap, ash, mb, dq, aow, pod, lm, tm, ak, ttc);

        books.forEach(book -> {
            book.getEditions().forEach(edition -> {
                edition.setBook(book);

                Optional.ofNullable(edition.getContributors())
                        .ifPresent(contributors -> contributors.forEach(c -> c.setEdition(edition)));
            });
        });

        bookRepository.saveAll(books);
    }

}
