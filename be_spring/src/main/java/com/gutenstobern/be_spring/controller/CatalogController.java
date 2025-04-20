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

import com.gutenstobern.be_spring.dto.BookEditionDTO;
import com.gutenstobern.be_spring.service.BookEditionService;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    @Autowired
    private BookEditionService bookEditionService;

    @GetMapping(path = { "", "/" })
    public ResponseEntity<List<BookEditionDTO>> getBookEditions() {
        return ResponseEntity.ok(bookEditionService.getBookEditions());
    }

    @GetMapping("/{editionId}")
    public ResponseEntity<BookEditionDTO> getBookEdition(@PathVariable Long editionId) {
        return bookEditionService.getBookEdition(editionId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Book edition not found"));
    }
}
