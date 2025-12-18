package com.example.demo.model;
import jakarta.persistence.*;
public class VolunteerProfile{
    @Id
    private Long id;
    private String  volunteerId;
    private String fullName;
    @Column(unique=true)
    private String email;
    private String phone,
    private String AVAILABLE;
    private String BUSY;
    private String INACTIVE;
    private LocalDateTime createdAt;
    
}