package com.example.api.service;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendEmail(String to, String subject, String body) {
        System.out.println("EMAIL to " + to + ": " + subject + " - " + body);
    }
}

