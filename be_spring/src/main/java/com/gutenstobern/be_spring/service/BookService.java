package com.gutenstobern.be_spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutenstobern.be_spring.dto.AuthorDTO;
import com.gutenstobern.be_spring.dto.BookEditionDTO;
import com.gutenstobern.be_spring.dto.ContributorDTO;
import com.gutenstobern.be_spring.entity.Book;
import com.gutenstobern.be_spring.entity.Edition;
import com.gutenstobern.be_spring.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Optional<BookEditionDTO> getBook(Long id) {
        return bookRepository.findById(id)
                .map(b -> generateBookDTO(b));
    }

    public List<BookEditionDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(b -> generateBookDTO(b))
                .toList();
    }

    private BookEditionDTO generateBookDTO(Book book) {
        BookEditionDTO bookEdition = new BookEditionDTO();
        bookEdition.setBookId(book.getId());
        bookEdition.setOriginalTitle(book.getTitleOriginal());
        bookEdition.setAuthors(book.getAuthors()
                .stream().map(a -> new AuthorDTO(a.getId(), a.getName()))
                .toList());

        Optional<Edition> edition = book.getEditions()
                .stream().findFirst();

        edition.ifPresent(edit -> {
            bookEdition.setEditionId(edit.getId());
            bookEdition.setEditionTitle(edit.getTitle());
            bookEdition.setDescription(edit.getDescription());
            bookEdition.setLink(edit.getProjectGutenbergLink());
            bookEdition.setCover(edit.getCoverURL());
            bookEdition.setPages(edit.getPages());
            bookEdition.setPublicationYear(edit.getPublicationYear());
            bookEdition.setLanguageName(edit.getLanguage().getName());
            bookEdition.setLanguageCode(edit.getLanguage().getIsoCode());

            bookEdition.setContributors(edit.getContributors()
                    .stream()
                    .map(c -> new ContributorDTO(c.getAuthor().getId(), c.getAuthor().getName(), c.getRole().getName()))
                    .toList());
        });

        return bookEdition;

    }
}
