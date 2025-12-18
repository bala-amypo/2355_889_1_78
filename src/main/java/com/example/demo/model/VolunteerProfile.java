package com.example.demo.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(
    name = "volunteer_profile",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "volunteerId"),
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "phone")
    }
)
public class VolunteerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String volunteerId;
    private String fullName;

    @Column(unique = true)
    private String email;

    private String phone;

    // AVAILABLE / BUSY / INACTIVE
    private String availabilityStatus;

    private LocalDateTime createdAt;

    // 🔹 Non-parameterized constructor
    public VolunteerProfile() {
    }

    // 🔹 Parameterized constructor
    public VolunteerProfile(String volunteerId, String fullName, String email,
                            String phone, String availabilityStatus) {
        this.volunteerId = volunteerId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.availabilityStatus = availabilityStatus;
    }

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    // 🔹 Getters & Setters
    public Long getId() {
        return id;
    }

    public String getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(String volunteerId) {
        this.volunteerId = volunteerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
