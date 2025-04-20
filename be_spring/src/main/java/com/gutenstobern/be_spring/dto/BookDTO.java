package com.gutenstobern.be_spring.dto;

import java.util.List;

import com.gutenstobern.be_spring.entity.Book;

public class BookDTO {
    Long id;
    String originalTitle;
    List<AuthorDTO> authors;

    public BookDTO() {
    }

    public BookDTO(Book book) {
        id = book.getId();
        originalTitle = book.getTitleOriginal();
        authors = book.getAuthors()
                .stream()
                .map(author -> new AuthorDTO(author.getId(), author.getName()))
                .toList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String title) {
        this.originalTitle = title;
    }

    public List<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDTO> authors) {
        this.authors = authors;
    }
}
