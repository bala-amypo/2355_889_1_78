package com.example.demo.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class CustomUserDetailsService implements UserDetailsService {
    private final Map<String, Map<String, Object>> users = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Map<String, Object> registerUser(String name, String email, String encodedPassword, String role) {
        Map<String, Object> user = new HashMap<>();
        long userId = idGenerator.getAndIncrement();
        user.put("userId", userId);
        user.put("name", name);
        user.put("email", email);
        user.put("password", encodedPassword);
        user.put("role", role);
        users.put(email, user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String, Object> user = users.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.withUsername(username)
                .password((String) user.get("password"))
                .roles((String) user.get("role"))
                .build();
    }
}