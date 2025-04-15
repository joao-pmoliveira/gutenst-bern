package com.gutenstobern.be_spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutenstobern.be_spring.dto.BookDTO;
import com.gutenstobern.be_spring.dto.EditionDTO;
import com.gutenstobern.be_spring.dto.LanguageDTO;
import com.gutenstobern.be_spring.entity.Book;
import com.gutenstobern.be_spring.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Optional<BookDTO> getBook(Long id) {
        return bookRepository.findById(id)
                .map(b -> generateBookDTO(b, false, false));
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(b -> generateBookDTO(b, false, false))
                .toList();
    }

    Optional<BookDTO> getBookWithEditions(Long id) {
        return bookRepository.findById(id)
                .map(b -> generateBookDTO(b, false, true));
    }

    private BookDTO generateBookDTO(Book book, boolean includeAuthors, boolean includeEditions) {
        BookDTO bookDTO = new BookDTO(book.getId(), book.getTitleOriginal());

        if (includeAuthors) {
            // TODO: generate authorDTO
        }

        if (includeEditions) {
            bookDTO.setEditions(book.getEditions()
                    .stream()
                    .map(edition -> {
                        LanguageDTO languageDTO = new LanguageDTO(edition.getLanguage().getIsoCode(),
                                edition.getLanguage().getName());

                        return new EditionDTO(
                                edition.getId(),
                                edition.getTitle(),
                                edition.getDescription(),
                                edition.getPages(),
                                edition.getPublicationYear(),
                                languageDTO, // lang
                                null, // authors
                                null); // book
                    })
                    .toList());
        }

        return bookDTO;
    }
}
