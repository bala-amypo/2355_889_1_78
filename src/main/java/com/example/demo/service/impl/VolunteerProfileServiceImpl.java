package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.VolunteerProfile;
import com.example.demo.repository.VolunteerProfileRepository;
import java.util.List;

public class VolunteerProfileServiceImpl {

    private final VolunteerProfileRepository repo;

    public VolunteerProfileServiceImpl(VolunteerProfileRepository repo) {
        this.repo = repo;
    }

    public VolunteerProfile createVolunteer(VolunteerProfile v) {
        if (repo.existsByEmail(v.getEmail()))
            throw new BadRequestException("Email already exists");
        return repo.save(v);
    }

    public VolunteerProfile getVolunteerById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public List<VolunteerProfile> getAllVolunteers() {
        return repo.findAll();
    }
}
