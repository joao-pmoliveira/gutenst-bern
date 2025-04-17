package com.gutenstobern.be_spring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "edition_contributor")
public class EditionContributor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    Author author;

    @ManyToOne
    @JoinColumn(name = "edition_id", referencedColumnName = "id")
    Edition edition;

    @ManyToOne
    @JoinColumn(name = "edition_contributor_role_id", referencedColumnName = "id")
    ContributorRole role;

    public EditionContributor() {
    }

    public EditionContributor(ContributorRole role, Author author) {
        this.role = role;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public Edition getEdition() {
        return edition;
    }

    public ContributorRole getRole() {
        return role;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }
}
