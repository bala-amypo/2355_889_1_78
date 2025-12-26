package com.example.demo.controller;

import com.example.demo.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CustomUserDetailsService userService;
    private final JwtTokenProvider tokenProvider;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public AuthController(CustomUserDetailsService userService,
                          JwtTokenProvider tokenProvider,
                          BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody RegisterRequest request) {
        String encodedPassword = encoder.encode(request.getPassword());
        var userMap = userService.registerUser(
                request.getName(),
                request.getEmail(),
                encodedPassword,
                request.getRole()
        );

        // Create authentication object (password is not checked here, just for token generation)
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()  // not really used for validation here
        );

        Long userId = (Long) userMap.get("userId");
        String role = (String) userMap.get("role");

        String token = tokenProvider.generateToken(auth, userId, role);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        UserDetails userDetails = userService.loadUserByUsername(request.getEmail());

        if (!encoder.matches(request.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        var userInfo = userService.getUserInfo(request.getEmail());

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                null,
                userDetails.getAuthorities()
        );

        String token = tokenProvider.generateToken(
                auth,
                (Long) userInfo.get("userId"),
                (String) userInfo.get("role")
        );

        return ResponseEntity.ok(new JwtResponse(token));
    }
}