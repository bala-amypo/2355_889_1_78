package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // CSRF disable (Swagger + POST requests ku thevai)
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // ✅ HelloServlet (tests + servlet access)
                .requestMatchers("/hello", "/hello/**").permitAll()

                // ✅ Task APIs (Swagger-la POST /tasks 403 poganum na)
                .requestMatchers("/tasks", "/tasks/**").permitAll()

                // ✅ Swagger UI
                .requestMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/swagger-ui.html"
                ).permitAll()

                // ✅ Authentication APIs
                .requestMatchers("/auth/**").permitAll()

                // ❌ Remaining ellam secured
                .anyRequest().authenticated()
            );

        return http.build();
    }

    // ✅ AuthenticationManager bean (Spring Boot 3 ku mandatory)
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // ✅ Password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
