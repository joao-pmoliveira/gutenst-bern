package com.gutenstobern.be_spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gutenstobern.be_spring.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    public Optional<Book> findByTitleOriginal(String titleOriginal);

    public List<Book> findByTitleOriginalContaining(String search);

}
