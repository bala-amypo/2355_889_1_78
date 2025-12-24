package com.example.demo.security;

import io.jsonwebtoken.*;
import java.util.*;
import org.springframework.security.core.Authentication;

public class JwtTokenProvider {

    private final String secret;
    private final long expiry;

    public JwtTokenProvider(String s, long e) {
        this.secret = s;
        this.expiry = e;
    }

    public String generateToken(Authentication auth, Long userId, String role) {
        return Jwts.builder()
                .setSubject(auth.getName())
                .claim("email", auth.getName())
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiry))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return getAllClaims(token).getSubject();
    }

    public Map<String, Object> getAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
