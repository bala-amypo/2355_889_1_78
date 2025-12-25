package com.example.demo.controller;

import com.example.demo.model.VolunteerProfile;
import com.example.demo.service.VolunteerProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/volunteers")
public class VolunteerController {
    private final VolunteerProfileService service;

    public VolunteerController(VolunteerProfileService service) {
        this.service = service;
    }

    @PostMapping
    public VolunteerProfile create(@RequestBody VolunteerProfile profile) {
        return service.createVolunteer(profile);
    }

    @GetMapping
    public List<VolunteerProfile> getAll() {
        return service.getAllVolunteers();
    }

    @GetMapping("/{id}")
    public VolunteerProfile getById(@PathVariable Long id) {
        return service.getVolunteerById(id);
    }
}