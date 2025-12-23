package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;

public interface UserService {
    User saveUser(User user);
    AuthResponse login(AuthRequest request);
    User findByEmail(String email);
    boolean existsByEmail(String email);
}