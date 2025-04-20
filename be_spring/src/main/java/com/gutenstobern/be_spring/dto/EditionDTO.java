package com.gutenstobern.be_spring.dto;

import java.util.List;

import com.gutenstobern.be_spring.entity.Edition;

public class EditionDTO {
    Long id;
    Long bookId;
    String title;
    String description;
    String link;
    String cover;
    int publicationYear;
    int pages;
    List<ContributorDTO> contributors;
    LanguageDTO language;

    public EditionDTO() {
    }

    public EditionDTO(Edition edition) {
        this.id = edition.getId();
        this.bookId = edition.getBook().getId();
        this.contributors = edition.getContributors()
                .stream()
                .map(contributor -> new ContributorDTO(
                        contributor.getAuthor(),
                        contributor.getRole()))
                .toList();
        this.language = new LanguageDTO(edition.getLanguage());
        this.title = edition.getTitle();
        this.description = edition.getDescription();
        this.link = edition.getProjectGutenbergLink();
        this.cover = edition.getCoverURL();
        this.publicationYear = edition.getPublicationYear();
        this.pages = edition.getPages();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<ContributorDTO> getContributors() {
        return contributors;
    }

    public void setContributors(List<ContributorDTO> contributors) {
        this.contributors = contributors;
    }

    public LanguageDTO getLanguage() {
        return language;
    }

    public void setLanguage(LanguageDTO language) {
        this.language = language;
    }
}
