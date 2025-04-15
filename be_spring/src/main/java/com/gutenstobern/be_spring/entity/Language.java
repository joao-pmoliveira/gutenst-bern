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
@Table(name = "language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 5, unique = true, nullable = false)
    String isoCode;

    @Column(length = 255, unique = true, nullable = false)
    String name;

    @OneToMany(mappedBy = "language")
    Set<Edition> editions;

    public Language() {
    }

    public Language(String isoCode, String name) {
        this.isoCode = isoCode;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public String getName() {
        return name;
    }

    public Set<Edition> getEditions() {
        return editions;
    }
}
