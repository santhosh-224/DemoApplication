package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserRequest;
import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        modelMapper = new ModelMapper();
        userService = new UserService(userRepository, modelMapper);
    }

    @Test
    void testGetAllUsers() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john@example.com");

        when(userRepository.findAll()).thenReturn(List.of(user));

        // Act
        List<UserDTO> result = userService.getAllUsers();

        // Assert
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testSaveUser() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setName("Jane Doe");
        user.setEmail("jane@example.com");

        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        UserDTO result = userService.saveUser(user);

        // Assert
        assertNotNull(result);
        assertEquals("Jane Doe", result.getName());
        assertEquals("jane@example.com", result.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testSaveUserWithBooks() {
        // Arrange
        UserRequest request = new UserRequest();
        request.setName("Alice");
        request.setEmail("alice@example.com");

        UserRequest.BookRequest bookReq = new UserRequest.BookRequest();
        bookReq.setTitle("Spring Boot Basics");
        bookReq.setAuthor("Author X");
        request.setBooks(List.of(bookReq));

        User user = new User();
        user.setId(1L);
        user.setName("Alice");
        user.setEmail("alice@example.com");

        Book book = new Book();
        book.setId(101L);
        book.setTitle("Spring Boot Basics");
        book.setAuthor("Author X");
        book.setUser(user);

        user.setBooks(List.of(book));

        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        UserDTO result = userService.saveUserWithBooks(request);

        // Assert
        assertNotNull(result);
        assertEquals("Alice", result.getName());
        assertEquals(1, result.getBooks().size());
        assertEquals("Spring Boot Basics", result.getBooks().get(0).getTitle());
        verify(userRepository, times(1)).save(any(User.class));
    }
}
