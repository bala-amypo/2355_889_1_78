package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.example.demo.model.VolunteerProfile;
import com.example.demo.repository.VolunteerProfileRepository;
import com.example.demo.service.VolunteerProfileService;

@Service
public class VolunteerProfileServiceImpl implements VolunteerProfileService {

    @Autowired
    VolunteerProfileRepository repo;

    @Override
    public VolunteerProfile createVolunteer(VolunteerProfile profile) {
        return repo.save(profile);
    }

    @Override
    public VolunteerProfile getVolunteerById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<VolunteerProfile> getAllVolunteers() {
        return repo.findAll();
    }

    @Override
    public VolunteerProfile findByVolunteerId(String volunteerId) {
        if (volunteerId == null) return null;
        return repo.findByVolunteerId(volunteerId);
    }

    @Override
    public VolunteerProfile updateAvailability(Long id, String availabilityStatus) {
        VolunteerProfile v = repo.findById(id).orElse(null);
        if (v != null) {
            v.setAvailabilityStatus(availabilityStatus);
            return repo.save(v);
        }
        return null;
    }
}