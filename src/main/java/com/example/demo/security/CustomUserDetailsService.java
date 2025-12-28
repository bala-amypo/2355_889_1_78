// package com.example.demo.security;

// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// import java.util.HashMap;
// import java.util.Map;
// import java.util.concurrent.atomic.AtomicLong;

// public class CustomUserDetailsService implements UserDetailsService {
//     private final Map<String, Map<String, Object>> users = new HashMap<>();
//     private final AtomicLong idGenerator = new AtomicLong(1);

//     public Map<String, Object> registerUser(String name, String email, String encodedPassword, String role) {
//         Map<String, Object> user = new HashMap<>();
//         long userId = idGenerator.getAndIncrement();
//         user.put("userId", userId);
//         user.put("name", name);
//         user.put("email", email);
//         user.put("password", encodedPassword);
//         user.put("role", role);
//         users.put(email, user);
//         return user;
//     }

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         Map<String, Object> user = users.get(username);
//         if (user == null) {
//             throw new UsernameNotFoundException("User not found");
//         }
//         return User.withUsername(username)
//                 .password((String) user.get("password"))
//                 .roles((String) user.get("role"))
//                 .build();
//     }
// }

package com.example.demo.security;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
