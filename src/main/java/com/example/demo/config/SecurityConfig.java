package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
            // ✅ CORS ENABLE
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))

            // ✅ CSRF DISABLED (Swagger + POST)
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // ✅ VERY IMPORTANT (Swagger preflight)
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                // ✅ PUBLIC ENDPOINTS
                .requestMatchers("/hello", "/hello/**").permitAll()
                .requestMatchers("/tasks", "/tasks/**").permitAll()

                // ✅ SWAGGER
                .requestMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/swagger-ui.html"
                ).permitAll()

                // ✅ AUTH
                .requestMatchers("/auth/**").permitAll()

                // ✅ EXTRA PUBLIC APIs
                .requestMatchers("/volunteers", "/volunteers/**").permitAll()
                .requestMatchers("/skills", "/skills/**").permitAll()
                .requestMatchers("/assignments", "/assignments/**").permitAll()
                .requestMatchers("/evaluations", "/evaluations/**").permitAll()

                // 🔒 ALL OTHERS NEED AUTH
                .anyRequest().authenticated()
                .authorizeHttpRequests(auth -> auth

    // existing code (unchanged)
    .requestMatchers("/hello", "/hello/**").permitAll()
    .requestMatchers("/tasks", "/tasks/**").permitAll()
    .requestMatchers(
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-ui.html"
    ).permitAll()
    .requestMatchers("/auth/**").permitAll()
    .requestMatchers("/volunteers", "/volunteers/**").permitAll()
    .requestMatchers("/skills", "/skills/**").permitAll()
    .requestMatchers("/assignments", "/assignments/**").permitAll()
    .requestMatchers("/evaluations", "/evaluations/**").permitAll()

    // 🔥 ADD ONLY THIS
    .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()

    .anyRequest().authenticated()
);

            );

        return http.build();
    }

    // ✅ FIXED CORS CONFIG (403 SOLUTION)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // ❗ DO NOT USE setAllowedOrigins("*")
        config.addAllowedOriginPattern("*");
        config.setAllowedMethods(
                List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
        );
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    // ✅ AUTH MANAGER
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // ✅ PASSWORD ENCODER
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
