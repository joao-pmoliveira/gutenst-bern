package com.gutenstobern.be_spring.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "edition")
public class Edition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    Book book;

    @OneToMany(mappedBy = "edition", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<EditionContributor> contributors;

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    Language language;

    public Edition() {
    }

    public Edition(String title,
            String description,
            String projectGutenbergLink,
            String coverURL,
            Language language,
            int publicationYear,
            int pages,
            Set<EditionContributor> contributors) {
        this.title = title;
        this.description = description;
        this.projectGutenbergLink = projectGutenbergLink;
        this.coverURL = coverURL;
        this.language = language;
        this.publicationYear = publicationYear;
        this.pages = pages;
        this.contributors = contributors;
    }

    @Column(length = 255)
    String title;
    @Column(length = 255)
    String description;
    @Column(length = 255)
    String projectGutenbergLink;
    @Column(length = 255)
    String coverURL;
    int publicationYear;
    int pages;

    public Long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public Set<EditionContributor> getContributors() {
        return contributors;
    }

    public Language getLanguage() {
        return language;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getProjectGutenbergLink() {
        return projectGutenbergLink;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public int getPages() {
        return pages;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
