package com.gutenstobern.be_spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gutenstobern.be_spring.dto.EditionDTO;
import com.gutenstobern.be_spring.service.EditionService;

@RestController
@RequestMapping("/api/editions")
public class EditionController {

    @Autowired
    private EditionService editionService;

    @GetMapping(path = { "", "/" })
    public ResponseEntity<List<EditionDTO>> getEditions() {
        return ResponseEntity.ok(editionService.getEditions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditionDTO> getEdition(@PathVariable Long id) {
        return editionService.getEdition(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Edition not found"));
    }
}
