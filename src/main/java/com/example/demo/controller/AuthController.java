package com.example.demo.controller;

import com.example.demo.model.AuthRequest;
import com.example.demo.model.AuthResponse;
import com.example.demo.security.JwtUtil;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    @Operation(summary = "Login user")
    public AuthResponse login(@RequestBody AuthRequest request) {

        // (In real project → username/password validate pannum)
        String token = jwtUtil.generateToken(request.getUsername());

        return new AuthResponse(token);
    }

    @PostMapping("/register")
    @Operation(summary = "Register user")
    public AuthResponse register(@RequestBody AuthRequest request) {
        return new AuthResponse("User registered successfully");
    }
}
