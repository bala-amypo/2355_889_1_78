package com.example.demo.service;

import com.example.demo.model.*;
import java.util.List;
import java.util.Optional;

public interface VolunteerProfileService {
    VolunteerProfile createVolunteer(VolunteerProfile profile);
    Optional<VolunteerProfile> findByVolunteerId(String volunteerId);
    VolunteerProfile getVolunteerById(Long id);
    List<VolunteerProfile> getAllVolunteers();
}