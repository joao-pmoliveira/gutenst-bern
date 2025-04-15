package com.gutenstobern.be_spring.dto;

import java.util.Set;

import com.gutenstobern.be_spring.entity.Book;
import com.gutenstobern.be_spring.entity.EditionContributor;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

public class AuthorDTO {
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "authors")
    Set<Book> books;

    @OneToMany(mappedBy = "author")
    Set<EditionContributor> contributions;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public Set<EditionContributor> getContributions() {
        return contributions;
    }

}
