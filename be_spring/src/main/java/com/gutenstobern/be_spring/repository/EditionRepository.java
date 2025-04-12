package com.gutenstobern.be_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gutenstobern.be_spring.entity.Edition;

@Repository
public interface EditionRepository extends JpaRepository<Edition, Long> {

}
