package com.example.demo.util;

import com.example.demo.entity.Book;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class TestDataUtil {
    public static User createRegularUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Regular User");
        user.setEmail("user@example.com");
        user.setPassword("password");
        user.setRole(Role.ROLE_USER);
        return user;
    }

    public static User createAdminUser() {
        User user = new User();
        user.setId(2L);
        user.setName("Admin User");
        user.setEmail("admin@example.com");
        user.setPassword("password");
        user.setRole(Role.ROLE_ADMIN);
        return user;
    }

    public static List<Book> createSampleBooks() {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Spring Boot Guide");
        book1.setAuthor("John Doe");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Java Programming");
        book2.setAuthor("Jane Smith");

        return Arrays.asList(book1, book2);
    }

    public static List<Book> createSampleBooksWithUser(User user) {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Spring Boot Guide");
        book1.setAuthor("John Doe");
        book1.setUser(user);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Java Programming");
        book2.setAuthor("Jane Smith");
        book2.setUser(user);

        return Arrays.asList(book1, book2);
    }

    public static Book createSampleBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        return book;
    }

    public static Book createSampleBookWithUser(User user) {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setUser(user);
        return book;
    }

    // Helper method to create book without ID (for testing creation)
    public static Book createNewBook() {
        Book book = new Book();
        book.setTitle("New Book");
        book.setAuthor("New Author");
        return book;
    }

    public static Book createNewBookWithUser(User user) {
        Book book = new Book();
        book.setTitle("New Book");
        book.setAuthor("New Author");
        book.setUser(user);
        return book;
    }

}