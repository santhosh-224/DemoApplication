package com.example.demo.service;

import com.example.demo.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    public List<Book> getAllBooks(){
        return List.of(
                new Book(1, "Harry Potter", "J K Rowling"),
                new Book(2, "Python for Everybody", "Chuck"),
                new Book(3, "Ponniyin Selvan", "Kalki")
        );
    }
}
