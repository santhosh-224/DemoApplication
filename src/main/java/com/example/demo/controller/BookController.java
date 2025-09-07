package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController (BookService bookService) {
        this.bookService = bookService;
    }

    //CREATE
    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        return bookService.addBook(book);
    }

    //READ ALL
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    //READ ONE
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    //UPDATE
    @PutMapping("/{id}")
    public Book updateBookById(@PathVariable int id, @RequestBody Book bookDetails) {
        return bookService.updateBookById(id, bookDetails);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        return bookService.deleteBookById(id);
    }

    @GetMapping("/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author){
        return bookService.getBooksByAuthor(author);
    }

    @GetMapping("/title-length/{len}")
    public List<Book> getBooksByTitleLength(@PathVariable int len){
        return bookService.getBooksByTitleLength(len);
    }

    @GetMapping("/count/{author}")
    public long countBooksByAuthor(@PathVariable String author){
        return bookService.countBooksByAuthor(author);
    }
}
