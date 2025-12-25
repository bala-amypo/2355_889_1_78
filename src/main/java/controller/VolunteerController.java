package com.example.demo.controller;

import com.example.demo.model.VolunteerProfile;
import com.example.demo.model.VolunteerSkillRecord;
import com.example.demo.service.VolunteerProfileService;
import com.example.demo.service.VolunteerSkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/volunteers")
public class VolunteerController {
    
    private final VolunteerProfileService profileService;
    private final VolunteerSkillService skillService;
    
    public VolunteerController(VolunteerProfileService profileService,
                              VolunteerSkillService skillService) {
        this.profileService = profileService;
        this.skillService = skillService;
    }
    
    @PostMapping
    public ResponseEntity<VolunteerProfile> createVolunteer(@RequestBody VolunteerProfile profile) {
        return ResponseEntity.ok(profileService.createVolunteer(profile));
    }
    
    @GetMapping
    public ResponseEntity<List<VolunteerProfile>> getAllVolunteers() {
        return ResponseEntity.ok(profileService.getAllVolunteers());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<VolunteerProfile> getVolunteerById(@PathVariable Long id) {
        return ResponseEntity.ok(profileService.getVolunteerById(id));
    }
    
    @PostMapping("/{volunteerId}/skills")
    public ResponseEntity<VolunteerSkillRecord> addSkill(
            @PathVariable Long volunteerId,
            @RequestBody VolunteerSkillRecord skill) {
        skill.setVolunteerId(volunteerId);
        return ResponseEntity.ok(skillService.addOrUpdateSkill(skill));
    }
    
    @GetMapping("/{volunteerId}/skills")
    public ResponseEntity<List<VolunteerSkillRecord>> getSkills(@PathVariable Long volunteerId) {
        return ResponseEntity.ok(skillService.getSkillsByVolunteer(volunteerId));
    }
}