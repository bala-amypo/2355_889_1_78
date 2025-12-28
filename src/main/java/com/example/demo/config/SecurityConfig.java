package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // 🔥 ADD THIS
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))

            // ❌ CSRF disabled (already correct)
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // 🔥 ADD THIS (VERY IMPORTANT)
                .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()

                // ✅ OLD CODE – DO NOT CHANGE
                .requestMatchers("/hello", "/hello/**").permitAll()

                // ✅ OLD CODE – Task APIs
                .requestMatchers("/tasks", "/tasks/**").permitAll()

                // ✅ OLD CODE – Swagger
                .requestMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/swagger-ui.html"
                ).permitAll()

                // ✅ OLD CODE – Auth
                .requestMatchers("/auth/**").permitAll()

                // 🔥 EXTRA CODE – ONLY ADDITION
                .requestMatchers("/volunteers", "/volunteers/**").permitAll()
                .requestMatchers("/skills", "/skills/**").permitAll()
                .requestMatchers("/assignments", "/assignments/**").permitAll()
                .requestMatchers("/evaluations", "/evaluations/**").permitAll()

                // 🔒 Remaining secured
                .anyRequest().authenticated()
            );

        return http.build();
    }

    

    // 🔥 ADD THIS CORS BEAN
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    // ✅ OLD CODE – AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // ✅ OLD CODE – Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
