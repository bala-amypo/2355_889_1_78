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
    private String BEGINNER;
    private String INTERMEDIATE;
    private String EXPERT;
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
       this.BEGINNER = BEGINNER;
       this.INTERMEDIATE=INTERMEDIATE;
        this.EXPERT=EXPERT;
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
public class SkillLevel {

    private String BEGINNER;
    private String INTERMEDIATE;
    private String EXPERT;

    // Non-parameterized constructor
    public SkillLevel() {
    }

    // Parameterized constructor
    public SkillLevel(String BEGINNER, String INTERMEDIATE, String EXPERT) {
        this.BEGINNER = BEGINNER;
        this.INTERMEDIATE = INTERMEDIATE;
        this.EXPERT = EXPERT;
    }

    // Getter & Setter for BEGINNER
    public String getBEGINNER() {
        return BEGINNER;
    }

    public void setBEGINNER(String BEGINNER) {
        this.BEGINNER = BEGINNER;
    }

    // Getter & Setter for INTERMEDIATE
    public String getINTERMEDIATE() {
        return INTERMEDIATE;
    }

    public void setINTERMEDIATE(String INTERMEDIATE) {
        this.INTERMEDIATE = INTERMEDIATE;
    }

    // Getter & Setter for EXPERT
    public String getEXPERT() {
        return EXPERT;
    }

    public void setEXPERT(String EXPERT) {
        this.EXPERT = EXPERT;
    }
}
