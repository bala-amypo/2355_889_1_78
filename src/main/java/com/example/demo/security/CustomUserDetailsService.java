package com.example.demo.security;

import com.example.demo.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private final Map<String, User> userStore = new HashMap<>();
    
    public Map<String, Object> registerUser(String name, String email, String password, String role) {
        User user = new User();
        user.setUserId((long) (userStore.size() + 1));
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        
        userStore.put(email, user);
        
        Map<String, Object> response = new HashMap<>();
        response.put("userId", user.getUserId());
        response.put("name", user.getName());
        response.put("email", user.getEmail());
        response.put("role", user.getRole());
        
        return response;
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userStore.get(email);
        
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        
        return org.springframework.security.core.userdetails.User
            .withUsername(user.getEmail())
            .password(user.getPassword())
            .authorities(user.getRole())
            .build();
    }
    
    public User getUserByEmail(String email) {
        return userStore.get(email);
    }
}