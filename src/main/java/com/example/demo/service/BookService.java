package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
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
}
