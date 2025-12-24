package com.example.demo.dto;

public class VolunteerSkillRequest {

    private Long volunteerId;
    private String skillName;
    private String skillLevel;
    private boolean certified;

    public VolunteerSkillRequest() {}

    public Long getVolunteerId() { return volunteerId; }
    public void setVolunteerId(Long volunteerId) { this.volunteerId = volunteerId; }

    public String getSkillName() { return skillName; }
    public void setSkillName(String skillName) { this.skillName = skillName; }

    public String getSkillLevel() { return skillLevel; }
    public void setSkillLevel(String skillLevel) { this.skillLevel = skillLevel; }

    public boolean isCertified() { return certified; }
    public void setCertified(boolean certified) { this.certified = certified; }
}
