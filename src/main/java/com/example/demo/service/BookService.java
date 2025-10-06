package com.example.demo.service;

import com.example.demo.controller.BookController;
import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getBooksByTitleLength(int len) {
        return bookRepository.findByTitleLengthGreaterThan(len);
    }

    public long countBooksByAuthor(String author) {
        return bookRepository.countBooksByAuthor(author);
    }

    public ResponseEntity<Book> addBook(Book book) {
        return ResponseEntity.ok(bookRepository.save(book));
    }

    public Page<BookDTO> getAllBooks(Pageable pageable) {
        Page<Book> books;
        books = bookRepository.findAll(pageable);

        return books.map(book -> modelMapper.map(book, BookDTO.class));
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book updateBookById(Long id, Book bookDetails) {
        Book book = getBookById(id);
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        return bookRepository.save(book);
    }

    public String deleteBookById(Long id) {
        bookRepository.deleteById(id);
        return "Book deleted successfully!";
    }

    public List<Book> getBooksByUser(Long userId) {
        return bookRepository.findByUserId(userId);
    }

    public Page<BookDTO> searchBooksByTitle(String title, Pageable pageable) {
        Page<Book> books;
        books = bookRepository.findByTitleContainingIgnoringCase(title, pageable);
        return books.map(book -> modelMapper.map(book, BookDTO.class));
    }
}
