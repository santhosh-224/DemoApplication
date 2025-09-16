package com.example.demo.service;

import com.example.demo.dto.UserRequest;
import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User saveUserWithBooks(UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        List<Book> books = request.getBooks().stream().map(bookReq -> {
            Book book = new Book();
            book.setTitle(bookReq.getTitle());
            book.setAuthor(bookReq.getAuthor());
            book.setUser(user);
            return book;
        }).toList();

        user.setBooks(books);

        return userRepository.save(user);
    }

}
