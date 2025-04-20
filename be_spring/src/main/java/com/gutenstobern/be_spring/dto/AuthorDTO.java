package com.gutenstobern.be_spring.dto;

import com.gutenstobern.be_spring.entity.Author;

public class AuthorDTO {
    Long id;
    String name;

    public AuthorDTO() {
    }

    public AuthorDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public AuthorDTO(Author author) {
        this.id = author.getId();
        this.name = author.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
