package com.gutenstobern.be_spring.dto;

import java.util.List;

public class EditionDTO {

    private Long id;
    private String title;
    private String description;
    private int pages;
    private int publicationYear;
    private List<EditionContributorDTO> contributors;
    private LanguageDTO language;
    private BookDTO book;

    public EditionDTO(Long id, String title, String description,
            int pages, int year, LanguageDTO language,
            List<EditionContributorDTO> contributors,
            BookDTO book) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.pages = pages;
        this.publicationYear = year;
        this.language = language;
        this.contributors = contributors;
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPages() {
        return pages;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public LanguageDTO getLanguage() {
        return language;
    }

    public BookDTO getBook() {
        return book;
    }

    public List<EditionContributorDTO> getContributors() {
        return contributors;
    }

}
