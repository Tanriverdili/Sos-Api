package com.example.api.service;
import com.example.api.entity.UserEntity;
import com.example.api.enums.Role;
import com.example.api.repository.UserRepository;
import com.example.api.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public void register(UserEntity request) {
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(String.valueOf(Role.USER));
        userRepository.save(user);
    }
    public AuthService(UserRepository userRepository, UserService userService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
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


