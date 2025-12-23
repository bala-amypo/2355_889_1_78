package com.example.demo.service;

import com.example.demo.dto.VolunteerProfileRequest;
import com.example.demo.model.VolunteerProfile;
import java.util.List;

public interface VolunteerProfileService {
    VolunteerProfile createVolunteer(VolunteerProfileRequest request);
    List<VolunteerProfile> getAllVolunteers();
    VolunteerProfile getVolunteerById(Long id);
    List<VolunteerProfile> getAvailableVolunteers();
    VolunteerProfile updateAvailability(Long id, String status);
    VolunteerProfile findByVolunteerId(Long volunteerId);
}