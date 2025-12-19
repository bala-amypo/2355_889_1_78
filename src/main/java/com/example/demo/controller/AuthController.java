package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService userService;

    // ---------------- REGISTER ----------------
    @PostMapping("/register")
    public User register(@Valid @RequestBody User user) {

        if (userService.existsByEmail(user.getEmail())) {
            return null; // simple handling for Review-1
        }

        return userService.saveUser(user);
    }

    // ---------------- LOGIN ----------------
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User existingUser = userService.findByEmail(user.getEmail());

        if (existingUser == null) {
            return "User not found";
        }

        if (!existingUser.getPassword().equals(user.getPassword())) {
            return "Invalid password";
        }

        return "Login successful";
    }
}