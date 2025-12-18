package com.example.demo.service.impl;

import com.example.demo.model.VolunteerProfile;
import com.example.demo.repository.VolunteerProfileRepository;
import com.example.demo.service.VolunteerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VolunteerProfileServiceImpl implements VolunteerProfileService {

    @Autowired
    private VolunteerProfileRepository repository;

    @Override
    public VolunteerProfile createVolunteer(VolunteerProfile profile) {
        // Check duplicates by email or volunteerId
        if(repository.existsById(profile.getId())) {
            throw new RuntimeException("Volunteer already exists");
        }
        return repository.save(profile);
    }

    @Override
    public VolunteerProfile getVolunteerById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Volunteer not found"));
    }

    @Override
    public List<VolunteerProfile> getAllVolunteers() {
        return repository.findAll();
    }

    @Override
    public VolunteerProfile findByVolunteerId(String volunteerId) {
        return repository.findByVolunteerId(volunteerId)
                .orElseThrow(() -> new RuntimeException("Volunteer not found"));
    }

    @Override
    public VolunteerProfile updateAvailability(Long id, String availabilityStatus) {
        VolunteerProfile volunteer = getVolunteerById(id);
        volunteer.setAvailabilityStatus(availabilityStatus);
        return repository.save(volunteer);
    }
}
