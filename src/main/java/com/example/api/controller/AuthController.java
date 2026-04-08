package com.example.api.controller;




import com.example.api.enums.Role;
import com.example.api.entity.UserEntity;

import com.example.api.service.AuthService;
import com.example.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
        UserEntity user = UserEntity.builder()
                .username(body.get("username"))
                .email(body.get("email"))

                .password(body.get("password"))
                .role(String.valueOf(Role.valueOf(body.getOrDefault("role", "USER"))))
                .build();
        userService.register(user);
        return ResponseEntity.ok(Map.of("message", "User registered"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String token = authService.login(body.get("email"), body.get("password"));
        return ResponseEntity.ok(Map.of("token", token));
    }
}