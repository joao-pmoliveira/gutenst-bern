package com.gutenstobern.be_spring.dto;

public class ContributorDTO {
    Long authorId;
    String name;
    String role;

    public ContributorDTO() {
    }

    public ContributorDTO(Long authorId, String name, String role) {
        this.authorId = authorId;
        this.name = name;
        this.role = role;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
