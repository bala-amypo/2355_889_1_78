// src/main/java/com/example/demo/security/CustomUserDetailsService.java
package com.example.demo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final Map<Long, UserInfo> users = new HashMap<>();
    private long idCounter = 1;

    public Map<String, Object> registerUser(String name, String email, String encodedPassword, String role) {
        UserInfo user = new UserInfo();
        user.id = idCounter++;
        user.name = name;
        user.email = email;
        user.password = encodedPassword;
        user.role = role;
        users.put(user.id, user);
        return Map.of("userId", user.id, "role", user.role);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserInfo userInfo = users.values().stream()
                .filter(u -> u.email.equals(email))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userInfo.role));
        return new User(userInfo.email, userInfo.password, authorities);
    }

    private static class UserInfo {
        Long id;
        String name;
        String email;
        String password;
        String role;
    }
}