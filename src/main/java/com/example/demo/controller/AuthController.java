package com.example.demo.controller;

import com.example.demo.model.AuthRequest;
import com.example.demo.model.AuthResponse;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/register")
    @Operation(summary = "Register user")
    public AuthResponse register(@RequestBody AuthRequest request) {
        return new AuthResponse("User registered successfully");
    }

    @PostMapping("/login")
    @Operation(summary = "Login user")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return new AuthResponse("Login successful");
    }
}
