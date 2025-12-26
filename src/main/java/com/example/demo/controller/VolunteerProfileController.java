package com.example.demo.controller;

import com.example.demo.model.VolunteerProfile;
import com.example.demo.service.VolunteerProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/volunteers")
public class VolunteerProfileController {

    private final VolunteerProfileService service;

    public VolunteerProfileController(VolunteerProfileService service) {
        this.service = service;
    }

    @PostMapping
    public VolunteerProfile create(@RequestBody VolunteerProfile profile) {
        return service.createVolunteer(profile);
    }

    @GetMapping("/{id}")
    public VolunteerProfile get(@PathVariable Long id) {
        return service.getVolunteerById(id);
    }

    @GetMapping
    public List<VolunteerProfile> all() {
        return service.getAllVolunteers();
    }

    @PutMapping("/{id}/availability")
    public VolunteerProfile updateAvailability(
            @PathVariable Long id,
            @RequestParam String status) {

        return service.updateAvailability(id, status);
    }
}
