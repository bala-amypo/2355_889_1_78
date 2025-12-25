package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.VolunteerProfile;
import com.example.demo.repository.VolunteerProfileRepository;
import com.example.demo.service.VolunteerProfileService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VolunteerProfileServiceImpl implements VolunteerProfileService {
    private final VolunteerProfileRepository repository;

    public VolunteerProfileServiceImpl(VolunteerProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public VolunteerProfile createVolunteer(VolunteerProfile profile) {
        if (repository.existsByVolunteerId(profile.getVolunteerId())) {
            throw new BadRequestException("Volunteer ID already exists");
        }
        if (repository.existsByEmail(profile.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
        if (repository.existsByPhone(profile.getPhone())) {
            throw new BadRequestException("Phone already exists");
        }
        return repository.save(profile);
    }

    @Override
    public Optional<VolunteerProfile> findByVolunteerId(String volunteerId) {
        return repository.findByVolunteerId(volunteerId);
    }

    @Override
    public VolunteerProfile getVolunteerById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<VolunteerProfile> getAllVolunteers() {
        return repository.findAll();
    }
}