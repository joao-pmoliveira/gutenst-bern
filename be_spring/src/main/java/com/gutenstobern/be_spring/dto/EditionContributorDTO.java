package com.gutenstobern.be_spring.dto;

public class EditionContributorDTO {
    private Long id;
    private AuthorDTO author;
    private EditionDTO editionDTO;
    private String role;

    public EditionContributorDTO() {
    }

    public Long getId() {
        return id;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public EditionDTO getEditionDTO() {
        return editionDTO;
    }

    public String getRole() {
        return role;
    }
}
