package com.example.api.security;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordHasher {
    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        String hashGulcan = encoder.encode("123456");
        String hashAli = encoder.encode("456789");

        System.out.println("Gulcan hash: " + hashGulcan);
        System.out.println("Ali hash: " + hashAli);
    }
}