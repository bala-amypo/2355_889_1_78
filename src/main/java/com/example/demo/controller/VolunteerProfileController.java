package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.example.demo.model.VolunteerProfile;
import com.example.demo.service.VolunteerProfileService;

@RestController
@RequestMapping("/api/volunteers")
public class VolunteerProfileController {

    @Autowired
    VolunteerProfileService service;

    @PostMapping
    public VolunteerProfile create(@RequestBody VolunteerProfile profile) {
        return service.createVolunteer(profile);
    }

    @GetMapping("/{id}")
    public VolunteerProfile getById(@PathVariable Long id) {
        return service.getVolunteerById(id);
    }

    @GetMapping
    public List<VolunteerProfile> getAll() {
        return service.getAllVolunteers();
    }

    @GetMapping("/lookup/{volunteerId}")
    public VolunteerProfile lookup(@PathVariable String volunteerId) {
        return service.findByVolunteerId(volunteerId);
    }

    @PutMapping("/{id}/availability")
    public VolunteerProfile updateAvailability(
            @PathVariable Long id,
            @RequestParam String availabilityStatus) {
        return service.updateAvailability(id, availabilityStatus);
    }
}