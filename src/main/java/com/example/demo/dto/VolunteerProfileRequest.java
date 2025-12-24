package com.example.demo.dto;

public class VolunteerProfileRequest {

    private String volunteerId;
    private String fullName;
    private String email;
    private String phone;

    public VolunteerProfileRequest() {}

    public String getVolunteerId() { return volunteerId; }
    public void setVolunteerId(String volunteerId) { this.volunteerId = volunteerId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
