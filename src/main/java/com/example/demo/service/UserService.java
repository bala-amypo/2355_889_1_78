package com.example.demo.service;

import com.example.demo.model.*;
import java.util.List;

public interface UserService {
    User saveUser(User user);
    AuthResponse login(AuthRequest request);
    User findByEmail(String email);
    boolean existsByEmail(String email);
}