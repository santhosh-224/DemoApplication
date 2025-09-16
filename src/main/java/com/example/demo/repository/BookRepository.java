package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // Derived query
    List<Book> findByUserId(Long userId);

    //1. Find books by author
    @Query("SELECT b FROM Book b WHERE b.author = :author")
    List<Book> findByAuthor(@Param("author") String author);

    //2. Find book with length greater the given value
    @Query("SELECT b FROM Book b WHERE LENGTH(b.title) > :len")
    List<Book> findByTitleLengthGreaterThan(@Param("len") int len);

    //3. Count books by author
    @Query("SELECT COUNT(b) FROM Book b WHERE b.author = :author")
    long countBooksByAuthor(@Param("author") String author);

    // JPQL query
    @Query("SELECT b FROM Book b WHERE b.user.id = :userId")
    List<Book> findBooksByUser(@Param("userId") Long userId);
}
