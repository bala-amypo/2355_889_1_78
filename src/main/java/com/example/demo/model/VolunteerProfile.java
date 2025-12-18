package com.example.demo.model;

import java.time.*;
import jakarta.persistence.*;

@Entity
@Table(name = "volunteer_profile")
public class VolunteerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String volunteerId;
    private String fullName;

    @Column(unique = true)
    private String email;

    private String phone;
    private String AVAILABLE;
    private String BUSY;
    private String INACTIVE;

    private LocalDateTime createdAt;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public VolunteerProfile() {
    }

    public VolunteerProfile(Long id, String volunteerId, String fullName,
                            String email, String phone,
                            String AVAILABLE, String BUSY,
                            String INACTIVE, LocalDateTime createdAt) {
        this.id = id;
        this.volunteerId = volunteerId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.AVAILABLE = AVAILABLE;
        this.BUSY = BUSY;
        this.INACTIVE = INACTIVE;
        this.createdAt = createdAt;
    }
}
