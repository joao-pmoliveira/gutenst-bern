package com.gutenstobern.be_spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gutenstobern.be_spring.dto.BookDTO;
import com.gutenstobern.be_spring.entity.Book;
import com.gutenstobern.be_spring.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookDTO> getBooks() {
        return bookRepository
                .findAll()
                .stream()
                .map(this::toBookDTO)
                .toList();
    }

    public Optional<BookDTO> getBook(Long id) {
        return bookRepository
                .findById(id)
                .map(this::toBookDTO);
    }

    private BookDTO toBookDTO(Book book) {
        return new BookDTO(book);
    }
}
