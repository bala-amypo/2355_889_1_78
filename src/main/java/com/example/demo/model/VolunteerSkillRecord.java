package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

import java.time.LocalDateTime;

@Entity
public class VolunteerSkillRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long volunteerId;
    private String skillName;
    private String skillLevel;   // BEGINNER / INTERMEDIATE / EXPERT
    private boolean certified;

    private LocalDateTime updatedAt;

    @PrePersist
    public void setTime() {
        this.updatedAt = LocalDateTime.now();
    }

    // ---------------- GETTERS ----------------

    public Long getId() {
        return id;
    }

    public Long getVolunteerId() {
        return volunteerId;
    }

    public String getSkillName() {
        return skillName;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public boolean isCertified() {
        return certified;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // ---------------- SETTERS ----------------

    public void setId(Long id) {
        this.id = id;
    }

    public void setVolunteerId(Long volunteerId) {
        this.volunteerId = volunteerId;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public void setCertified(boolean certified) {
        this.certified = certified;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}