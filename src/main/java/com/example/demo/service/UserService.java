package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserRequest;
import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))   //entity -> dto
                .collect(Collectors.toList());
    }

    public UserDTO saveUser(User user) {
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);   //entity -> dto

    }

    public UserDTO saveUserWithBooks(UserRequest request) {
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

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

}
