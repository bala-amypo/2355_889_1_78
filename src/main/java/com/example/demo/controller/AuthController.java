package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository repository;
    private final JwtTokenProvider jwtProvider;
    private final PasswordEncoder encoder;

    public AuthController(
            UserRepository repository,
            JwtTokenProvider jwtProvider,
            PasswordEncoder encoder) {

        this.repository = repository;
        this.jwtProvider = jwtProvider;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User u = repository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!encoder.matches(user.getPassword(), u.getPassword()))
            throw new RuntimeException("Invalid credentials");

        return jwtProvider.generateToken(u.getUsername());
    }
}
