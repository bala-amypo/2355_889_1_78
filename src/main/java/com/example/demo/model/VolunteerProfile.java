package com.example.demo.model;

import java.time.*;
import jakarta.persistence.*;
public class VolunteerProfile {
@Entity
@Table anme=("Volunteer")
    @Id
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
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
    public String getAVAILABLE() {
        return AVAILABLE;
    }
    public void setAVAILABLE(String AVAILABLE) {
        this.AVAILABLE = AVAILABLE;
    }
    public String getBUSY() {
        return BUSY;
    }
    public void setBUSY(String BUSY) {
        this.BUSY = BUSY;
    }
    public String getINACTIVE() {
        return INACTIVE;
    }
    public void setINACTIVE(String INACTIVE) {
        this.INACTIVE = INACTIVE;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
