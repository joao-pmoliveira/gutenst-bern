package com.gutenstobern.be_spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gutenstobern.be_spring.entity.Edition;

@Repository
public interface EditionRepository extends JpaRepository<Edition, Long> {

    public List<Edition> findByBookId(Long bookId);

    public Optional<Edition> findByTitle(String title);

}
