package com.gutenstobern.be_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gutenstobern.be_spring.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
