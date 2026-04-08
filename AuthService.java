package com.example.api.service;




import com.example.api.entity.UserEntity;
import com.example.api.security.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;





@Service
public class AuthService {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserService userService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(String email, String password) {

        UserEntity user = userService.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("Email tapılmadı");
        }


        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Parol səhvdir");
        }


        return jwtUtil.generateToken(user.getEmail(), user.getRole());
    }
}
