package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtTokenProvider {

    private final String secret;
    private final long validityInMilliseconds;

    public JwtTokenProvider(String secret, long validityInMilliseconds) {
        this.secret = secret;
        this.validityInMilliseconds = validityInMilliseconds;
    }

    public String generateToken(Authentication authentication, Long userId, String role) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);                 // testcase expects this key
        claims.put("role", role);                     // testcase expects this key
        claims.put("email", authentication.getName());// testcase expects this key

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)                   // must be first
                .setSubject(authentication.getName())
                .setId(UUID.randomUUID().toString()) // makes token always unique
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret) // short key compatible
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Map<String, Object> getAllClaims(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return new HashMap<>(claims);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
