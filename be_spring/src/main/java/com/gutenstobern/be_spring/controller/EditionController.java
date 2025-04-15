package com.gutenstobern.be_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gutenstobern.be_spring.dto.EditionDTO;
import com.gutenstobern.be_spring.dto.LanguageDTO;
import com.gutenstobern.be_spring.entity.Edition;
import com.gutenstobern.be_spring.repository.EditionRepository;

@RestController
@RequestMapping("/api/editions")
public class EditionController {

    @Autowired
    private EditionRepository editionRepository;

    @GetMapping("/{id}")
    public ResponseEntity<EditionDTO> getEdition(@PathVariable Long id) {
        return editionRepository.findById(id)
                .map(this::genEditionDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    private EditionDTO genEditionDTO(Edition edition) {
        LanguageDTO languageDTO = new LanguageDTO(edition.getLanguage().getIsoCode(),
                edition.getLanguage().getName());

        // TODO: include authors
        return new EditionDTO(
                edition.getId(),
                edition.getTitle(),
                edition.getDescription(),
                edition.getPages(),
                edition.getPublicationYear(),
                languageDTO, null, null);
    }

}
