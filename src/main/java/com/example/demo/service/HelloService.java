package com.example.demo.service;

import com.example.demo.repository.DateRepository;
import com.example.demo.repository.HelloRepository;
import com.example.demo.repository.QuoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelloService {

    private final HelloRepository helloRepository;

    private final DateRepository dateRepository;

    private final QuoteRepository quoteRepository;

    public HelloService(HelloRepository helloRepository, DateRepository dateRepository, QuoteRepository quoteRepository) {
        this.helloRepository = helloRepository;
        this.dateRepository = dateRepository;
        this.quoteRepository = quoteRepository;
    }

    public String fetchMessage() {
        String message = helloRepository.getMessage();
        String today = dateRepository.getTodayDate();
        String quote = quoteRepository.getQuote();

        return message + " Today is " + today + " Quote: \"" + quote+"\"";
    }

    public List<String> fetchGreetings() {
        return helloRepository.getGreetings();

    }
}
