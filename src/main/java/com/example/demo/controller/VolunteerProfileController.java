// src/main/java/com/example/demo/controller/VolunteerProfileController.java
package com.example.demo.controller;

import com.example.demo.model.VolunteerProfile;
import com.example.demo.service.VolunteerProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/volunteers")
@Tag(name = "VolunteerProfileController")
public class VolunteerProfileController {

    @Autowired
    private VolunteerProfileService service;

    @PostMapping("/")
    @Operation(summary = "Create volunteer")
    public VolunteerProfile create(@RequestBody VolunteerProfile profile) {
        return service.createVolunteer(profile);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get volunteer")
    public VolunteerProfile get(@PathVariable Long id) {
        return service.getVolunteerById(id);
    }

    @GetMapping("/")
    @Operation(summary = "List all")
    public List<VolunteerProfile> list() {
        return service.getAllVolunteers();
    }

    @PutMapping("/{id}/availability")
    @Operation(summary = "Update status")
    public void updateAvailability(@PathVariable Long id, @RequestBody Map<String, String> body) {
        service.updateAvailability(id, body.get("status"));
    }

    @GetMapping("/lookup/{volunteerId}")
    @Operation(summary = "Lookup by ID")
    public VolunteerProfile lookup(@PathVariable String volunteerId) {
        return service.findByVolunteerId(volunteerId).orElseThrow();
    }
}