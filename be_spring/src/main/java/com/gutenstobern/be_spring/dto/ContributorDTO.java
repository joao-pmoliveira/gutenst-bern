package com.gutenstobern.be_spring.dto;

import com.gutenstobern.be_spring.entity.Author;
import com.gutenstobern.be_spring.entity.ContributorRole;
import com.gutenstobern.be_spring.entity.EditionContributor;

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

    public ContributorDTO(Author author, ContributorRole role) {
        this.authorId = author.getId();
        this.name = author.getName();
        this.role = role.getName();
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

    public static ContributorDTO from(EditionContributor contributor) {
        return new ContributorDTO(contributor.getAuthor(), contributor.getRole());
    }
}
