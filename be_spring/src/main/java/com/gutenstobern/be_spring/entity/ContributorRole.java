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
@Table(name = "edition_contributor_role")
public class ContributorRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 255, unique = true, nullable = false)
    String name;

    @OneToMany(mappedBy = "role")
    Set<EditionContributor> contributors;

    public ContributorRole() {
    }

    public ContributorRole(String name) {
        this.name = name;
    }

}
