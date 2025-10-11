package com.example.demo.service;


import com.example.demo.dto.BookDTO;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private static final Logger log = LoggerFactory.getLogger(BookService.class);

    @Autowired
    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    public List<Book> getBooksByAuthor(String author) {
        log.info("Fetching books by author: {}", author);
        try{
            List <Book> books = bookRepository.findByAuthor(author);
            log.debug("Found {} books by author: {}", books.size(), author);
            return books;
        }catch (Exception e) {
            log.error("Error fetching books by author: {}", author, e);
            throw e;
        }
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

    public Page<BookDTO> getBooks(String title, String author, Integer year, Pageable pageable) {
        Page<Book> books;

        if(author != null && year != null) {
            books = bookRepository.findByAuthorIgnoreCaseAndYear(author, year, pageable);
        } else if (author != null) {
            books = bookRepository.findByAuthorIgnoreCase(author, pageable);
        } else if (year != null) {
            books = bookRepository.findByYear(year, pageable);
        } else if (title != null) {
            books = bookRepository.findByTitleContainingIgnoringCase(title, pageable);
        }else {
            books = bookRepository.findAll(pageable);
        }

        return books.map(book -> modelMapper.map(book, BookDTO.class));
    }
}
