package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // find user by email (used in login)
    Optional<User> findByEmail(String email);

    // check duplicate email (used in register)
    boolean existsByEmail(String email);
}
