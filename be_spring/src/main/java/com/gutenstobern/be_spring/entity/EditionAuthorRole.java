package com.gutenstobern.be_spring.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "edition_author_role")
public class EditionAuthorRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 255, unique = true, nullable = false)
    String name;

    @OneToMany(mappedBy = "role")
    Set<EditionAuthor> editionAuthors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EditionAuthor> getEditionAuthors() {
        return editionAuthors;
    }

    public void setEditionAuthors(Set<EditionAuthor> editionAuthors) {
        this.editionAuthors = editionAuthors;
    }
}
