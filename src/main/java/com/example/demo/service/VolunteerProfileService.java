package com.example.demo.service;

import java.util.List;
import com.example.demo.model.VolunteerProfile;

public interface VolunteerProfileService {
    VolunteerProfile createVolunteer(VolunteerProfile profile);
    VolunteerProfile getVolunteerById(Long id);
    List<VolunteerProfile> getAllVolunteers();
    VolunteerProfile findByVolunteerId(String volunteerId);
    VolunteerProfile updateAvailability(Long id, String status);
}