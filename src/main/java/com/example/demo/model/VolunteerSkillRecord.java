package com.example.demo.model;

import java.time.LocalDateTime;

public class VolunteerSkillRecord {
    private Long volunteerId;
    private String skillName;
    private String skillLevel;
    private boolean certified;
    private LocalDateTime updatedAt;

    public Long getVolunteerId() { return volunteerId; }
    public void setVolunteerId(Long volunteerId) { this.volunteerId = volunteerId; }

    public String getSkillName() { return skillName; }
    public void setSkillName(String skillName) { this.skillName = skillName; }

    public String getSkillLevel() { return skillLevel; }
    public void setSkillLevel(String skillLevel) { this.skillLevel = skillLevel; }

    public boolean isCertified() { return certified; }
    public void setCertified(boolean certified) { this.certified = certified; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
