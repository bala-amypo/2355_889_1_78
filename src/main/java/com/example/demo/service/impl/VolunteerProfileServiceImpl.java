package com.example.demo.service.impl;

import com.example.demo.service.*;
import com.example.demo.repository.*;
import com.example.demo.model.*;
import com.example.demo.exception.BadRequestException;
import java.util.List;

public class VolunteerProfileServiceImpl implements VolunteerProfileService {

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
