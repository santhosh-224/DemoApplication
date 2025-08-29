package com.example.demo.repository;

import org.springframework.stereotype.Repository;

@Repository
public class QuoteRepository {
    public String getQuote() {
        return "Keep Pushing forward";
    }
}
