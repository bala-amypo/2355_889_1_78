package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String secretKey;
    private final long validity;

    public JwtTokenProvider(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.expiration}") long validity) {

        this.secretKey = secretKey;
        this.validity = validity;
    }

    // ✅ REQUIRED BY CONTROLLERS
    public String generateToken(String username) {
        return createToken(username);
    }

    // ✅ REQUIRED BY TEST CASES
    public String generateToken(
            Authentication authentication,
            Long userId,
            String role) {

        return createToken(authentication.getName());
    }

    private String createToken(String subject) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + validity);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
