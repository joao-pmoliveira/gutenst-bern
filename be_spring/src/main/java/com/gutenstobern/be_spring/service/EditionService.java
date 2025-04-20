package com.gutenstobern.be_spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutenstobern.be_spring.dto.EditionDTO;
import com.gutenstobern.be_spring.repository.EditionRepository;

@Service
public class EditionService {

    @Autowired
    EditionRepository editionRepository;

    public List<EditionDTO> getEditions() {
        return editionRepository
                .findAll()
                .stream()
                .map(EditionDTO::new)
                .toList();
    }

    public Optional<EditionDTO> getEdition(Long id) {
        return editionRepository
                .findById(id)
                .map(e -> new EditionDTO(e));
    }
}
