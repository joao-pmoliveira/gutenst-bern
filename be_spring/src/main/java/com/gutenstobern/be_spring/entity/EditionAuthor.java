package com.gutenstobern.be_spring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "edition_author")
public class EditionAuthor {
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
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    EditionAuthorRole role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public EditionAuthorRole getRole() {
        return role;
    }

    public void setRole(EditionAuthorRole role) {
        this.role = role;
    }

}
