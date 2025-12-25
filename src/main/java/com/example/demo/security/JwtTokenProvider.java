package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenProvider {

    private final String secret;
    private final long validityInMilliseconds;

    public JwtTokenProvider(String secret, long validityInMilliseconds) {
        this.secret = secret;
        this.validityInMilliseconds = validityInMilliseconds;
    }

    // =========================
    // TOKEN GENERATION
    // =========================
    public String generateToken(Authentication authentication, Long userId, String role) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);                  // testcase expects
        claims.put("role", role);                      // testcase expects
        claims.put("email", authentication.getName()); // testcase expects

        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)                     // MUST be first
                .setSubject(authentication.getName())  // username/email
                .setIssuedAt(now)
                .setExpiration(expiry)
                // HS256 works with short secret (IMPORTANT)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // =========================
    // GET USERNAME
    // =========================
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // =========================
    // GET ALL CLAIMS
    // =========================
    public Map<String, Object> getAllClaims(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return new HashMap<>(claims);
    }

    // =========================
    // VALIDATE TOKEN
    // =========================
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
