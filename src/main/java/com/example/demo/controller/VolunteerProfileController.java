package com.example.demo.controller;

import com.example.demo.dto.AvailabilityUpdateRequest;
import com.example.demo.dto.VolunteerProfileRequest;
import com.example.demo.model.VolunteerProfile;
import com.example.demo.service.VolunteerProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/volunteers")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Volunteer Profile", description = "Volunteer profile management")
public class VolunteerProfileController {
    
    private final VolunteerProfileService service;
    
    public VolunteerProfileController(VolunteerProfileService service) {
        this.service = service;
    }
    
    @PostMapping
    @Operation(summary = "Create volunteer profile")
    public ResponseEntity<VolunteerProfile> createVolunteer(@RequestBody VolunteerProfileRequest request) {
        return ResponseEntity.ok(service.createVolunteer(request));
    }
    
    @GetMapping
    @Operation(summary = "Get all volunteers")
    public ResponseEntity<List<VolunteerProfile>> getAllVolunteers() {
        return ResponseEntity.ok(service.getAllVolunteers());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get volunteer by ID")
    public ResponseEntity<VolunteerProfile> getVolunteer(@PathVariable Long id) {
        return ResponseEntity.ok(service.getVolunteerById(id));
    }
    
    @PutMapping("/{id}/availability")
    @Operation(summary = "Update volunteer availability status")
    public ResponseEntity<VolunteerProfile> updateAvailability(
            @PathVariable Long id, 
            @RequestBody AvailabilityUpdateRequest request) {
        return ResponseEntity.ok(service.updateAvailability(id, request.getStatus()));
    }
    
    @GetMapping("/lookup/{volunteerId}")
    @Operation(summary = "Lookup volunteer by volunteer ID")
    public ResponseEntity<VolunteerProfile> lookupVolunteer(@PathVariable Long volunteerId) {
        return ResponseEntity.ok(service.getVolunteerById(volunteerId));
    }
}