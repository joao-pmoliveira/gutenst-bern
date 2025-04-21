package com.gutenstobern.be_spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutenstobern.be_spring.dto.AuthorDTO;
import com.gutenstobern.be_spring.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<AuthorDTO> getAuthors() {
        return authorRepository
                .findAll()
                .stream()
                .map(AuthorDTO::new)
                .toList();
    }

    public Optional<AuthorDTO> getAuthor(Long id) {
        return authorRepository
                .findById(id)
                .map(AuthorDTO::new);
    }

}
