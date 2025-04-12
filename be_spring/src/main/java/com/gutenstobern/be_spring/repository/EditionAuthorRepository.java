package com.gutenstobern.be_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gutenstobern.be_spring.entity.EditionAuthor;

@Repository
public interface EditionAuthorRepository extends JpaRepository<EditionAuthor, Long> {

}
