package com.example.demo.model;

import java.time.*;
import jakarta.persistence.*;

@Entity
@Table(name = "volunteer_skill_record")
public class VolunteerSkillRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long volunteerId;

    private String skillName;

    // BEGINNER / INTERMEDIATE / EXPERT
    private String skillLevel;

    private Boolean certified;

    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt = LocalDateTime.now();
    }

    public VolunteerSkillRecord() {}

    public VolunteerSkillRecord(Long volunteerId, String skillName,
                                String skillLevel, Boolean certified) {
        this.volunteerId = volunteerId;
        this.skillName = skillName;
        this.skillLevel = skillLevel;
        this.certified = certified;
    }

    // getters & setters
    public Long getId() { return id; }
    public Long getVolunteerId() { return volunteerId; }
    public void setVolunteerId(Long volunteerId) { this.volunteerId = volunteerId; }
    public String getSkillName() { return skillName; }
    public void setSkillName(String skillName) { this.skillName = skillName; }
    public String getSkillLevel() { return skillLevel; }
    public void setSkillLevel(String skillLevel) { this.skillLevel = skillLevel; }
    public Boolean getCertified() { return certified; }
    public void setCertified(Boolean certified) { this.certified = certified; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
