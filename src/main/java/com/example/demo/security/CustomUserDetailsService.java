package com.example.demo.security;

import java.util.*;
import org.springframework.security.core.userdetails.*;

public class CustomUserDetailsService implements UserDetailsService {

    private final Map<String, Map<String, Object>> users = new HashMap<>();

    public Map<String, Object> registerUser(
            String name, String email, String password, String role) {

        Map<String, Object> u = new HashMap<>();
        u.put("userId", (long) (users.size() + 1));
        u.put("email", email);
        u.put("role", role);

        users.put(email, u);
        return u;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        if (!users.containsKey(email))
            throw new UsernameNotFoundException("User not found");
        return User.withUsername(email).password("N/A").authorities("USER").build();
    }
}
