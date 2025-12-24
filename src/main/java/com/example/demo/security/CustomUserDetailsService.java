package com.example.demo.security;

import org.springframework.security.core.userdetails.*;
import java.util.*;

public class CustomUserDetailsService implements UserDetailsService {

    private final Map<String, Map<String, Object>> store = new HashMap<>();
    private long id = 1;

    public Map<String, Object> registerUser(String name, String email,
                                            String password, String role) {
        Map<String, Object> user = new HashMap<>();
        user.put("userId", id++);
        user.put("email", email);
        user.put("password", password);
        user.put("role", role);
        store.put(email, user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        if (!store.containsKey(username))
            throw new UsernameNotFoundException("User not found");

        return User.withUsername(username)
                .password((String) store.get(username).get("password"))
                .authorities("ROLE_" + store.get(username).get("role"))
                .build();
    }
}
