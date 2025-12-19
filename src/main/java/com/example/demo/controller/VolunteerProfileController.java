package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.VolunteerProfile;
import com.example.demo.service.VolunteerProfileService;

@RestController
@RequestMapping("/api/volunteers")
@CrossOrigin
public class VolunteerProfileController {

    @Autowired
    private VolunteerProfileService service;

    @PostMapping
    public VolunteerProfile create(@RequestBody VolunteerProfile profile) {
        return service.createVolunteer(profile);
    }

    @GetMapping("/{id}")
    public VolunteerProfile get(@PathVariable Long id) {
        return service.getVolunteerById(id);
    }

    @GetMapping
    public List<VolunteerProfile> getAll() {
        return service.getAllVolunteers();
    }

    @PutMapping("/{id}/availability")
    public VolunteerProfile updateStatus(@PathVariable Long id, @RequestParam String status) {
        return service.updateAvailability(id, status);
    }

    @GetMapping("/lookup/{volunteerId}")
    public VolunteerProfile lookup(@PathVariable String volunteerId) {
        return service.findByVolunteerId(volunteerId);
    }
}