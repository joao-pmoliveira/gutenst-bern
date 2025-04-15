package com.gutenstobern.be_spring.dto;

import java.util.List;

public class BookDTO {
    private Long id;
    private String title;
    private List<EditionDTO> editions;
    private List<AuthorDTO> authors;

    public BookDTO() {
    };

    public BookDTO(Long id, String title, List<EditionDTO> editions) {
        this.id = id;
        this.title = title;
        this.editions = editions;
    }

    public BookDTO(Long id, String title) {
        this.id = id;
        this.title = title;
        this.editions = null;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<EditionDTO> getEditions() {
        return editions;
    }

    public List<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEditions(List<EditionDTO> editions) {
        this.editions = editions;
    }

    public void setAuthors(List<AuthorDTO> authors) {
        this.authors = authors;
    }
}
