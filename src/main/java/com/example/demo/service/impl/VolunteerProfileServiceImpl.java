// src/main/java/com/example/demo/service/VolunteerProfileService.java
package com.example.demo.service;

import com.example.demo.model.VolunteerProfile;

import java.util.List;
import java.util.Optional;

public interface VolunteerProfileService {
    VolunteerProfile createVolunteer(VolunteerProfile profile);
    VolunteerProfile getVolunteerById(Long id);
    List<VolunteerProfile> getAllVolunteers();
    Optional<VolunteerProfile> findByVolunteerId(String volunteerId);
    void updateAvailability(Long id, String status);
}