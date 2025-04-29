package com.gutenstobern.be_spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gutenstobern.be_spring.entity.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    public Optional<Language> findByName(String name);

    public Optional<Language> findByIsoCode(String isoCode);
}
