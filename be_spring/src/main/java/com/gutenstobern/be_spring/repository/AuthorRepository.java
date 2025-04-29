package com.gutenstobern.be_spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gutenstobern.be_spring.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    public Optional<Author> findByName(String name);
}
