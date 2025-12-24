package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public String generateToken(String username) {
        // dummy token (for now)
        return "dummy-jwt-token-for-" + username;
    }
}
