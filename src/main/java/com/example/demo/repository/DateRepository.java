package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class DateRepository {
    public String getTodayDate() {
        return LocalDate.now().toString();
    }
}
