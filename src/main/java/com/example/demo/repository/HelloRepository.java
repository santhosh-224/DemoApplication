package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HelloRepository {
    public String getMessage(){
        return "Hello from Repository!";
    }

    public List<String> getGreetings() {
        return List.of(
                "Hello World",
                "Hi there!",
                "Welcome to Spring Boot",
                "Good day!"
        );
    }
}
