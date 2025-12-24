package com.example.demo.security;

import org.springframework.security.core.userdetails.*;
import java.util.*;

public class CustomUserDetailsService implements UserDetailsService {

    private final Map<String, Map<String, Object>> users = new HashMap<>();

    public Map<String, Object> registerUser(
            String name, String email, String pwd, String role) {

        Map<String, Object> u = new HashMap<>();
        u.put("userId", (long) (users.size() + 1));
        u.put("role", role);
        users.put(email, u);
        return u;
    }

    public UserDetails loadUserByUsername(String email) {
        if (!users.containsKey(email))
            throw new UsernameNotFoundException("User not found");
        return User.withUsername(email).password("N/A").authorities("USER").build();
    }
}
