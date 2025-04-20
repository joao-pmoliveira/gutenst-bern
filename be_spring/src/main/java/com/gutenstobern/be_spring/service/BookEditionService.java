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
import com.gutenstobern.be_spring.repository.EditionRepository;

@Service
public class BookEditionService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    EditionRepository editionRepository;

    public List<BookEditionDTO> getBookEditions() {
        return bookRepository
                .findAll()
                .stream()
                .map(this::toBookEditionDTO)
                .toList();
    }

    public Optional<BookEditionDTO> getBookEdition(Long editionId) {
        return editionRepository
                .findById(editionId)
                .map(this::toBookEditionDTO);
    }

    private BookEditionDTO toBookEditionDTO(Book book) {
        BookEditionDTO be = new BookEditionDTO();
        be.setBookId(book.getId());
        be.setOriginalTitle(book.getTitleOriginal());
        be.setAuthors(book.getAuthors()
                .stream()
                .map(AuthorDTO::new)
                .toList());

        // TODO: pick best edition based on other params (ex: language, etc)
        Optional<Edition> edition = book.getEditions()
                .stream()
                .findFirst();

        edition.ifPresent(e -> {
            be.setEditionId(e.getId());
            be.setEditionTitle(e.getTitle());
            be.setDescription(e.getDescription());
            be.setLink(e.getProjectGutenbergLink());
            be.setCover(e.getCoverURL());
            be.setPages(e.getPages());
            be.setPublicationYear(e.getPublicationYear());
            be.setLanguageName(e.getLanguage().getName());
            be.setLanguageCode(e.getLanguage().getIsoCode());
            be.setContributors(e.getContributors()
                    .stream()
                    .map(ContributorDTO::from)
                    .toList());
        });

        return be;
    }

    private BookEditionDTO toBookEditionDTO(Edition edition) {
        BookEditionDTO be = new BookEditionDTO();
        be.setBookId(edition.getBook().getId());
        be.setOriginalTitle(edition.getBook().getTitleOriginal());
        be.setAuthors(edition.getBook().getAuthors()
                .stream()
                .map(AuthorDTO::new)
                .toList());

        be.setEditionId(edition.getId());
        be.setEditionTitle(edition.getTitle());
        be.setDescription(edition.getDescription());
        be.setLink(edition.getProjectGutenbergLink());
        be.setCover(edition.getCoverURL());
        be.setPages(edition.getPages());
        be.setPublicationYear(edition.getPublicationYear());
        be.setLanguageName(edition.getLanguage().getName());
        be.setLanguageCode(edition.getLanguage().getIsoCode());
        be.setContributors(edition.getContributors()
                .stream()
                .map(ContributorDTO::from)
                .toList());

        return be;
    }
}
