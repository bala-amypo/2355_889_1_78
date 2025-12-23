package com.example.demo.controller;

import com.example.demo.dto.VolunteerSkillRequest;
import com.example.demo.model.VolunteerSkillRecord;
import com.example.demo.service.VolunteerSkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Volunteer Skills", description = "Volunteer skill management")
public class VolunteerSkillController {

    private final VolunteerSkillService service;

    public VolunteerSkillController(VolunteerSkillService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Add or update volunteer skill")
    public ResponseEntity<VolunteerSkillRecord> addSkill(
            @RequestBody VolunteerSkillRequest request) {
        return ResponseEntity.ok(service.addOrUpdateSkill(request));
    }

    @GetMapping("/volunteer/{volunteerId}")
    @Operation(summary = "Get skills by volunteer ID")
    public ResponseEntity<List<VolunteerSkillRecord>> getSkillsByVolunteer(
            @PathVariable Long volunteerId) {
        return ResponseEntity.ok(service.getSkillsByVolunteerId(volunteerId));
    }

    @GetMapping("/name/{skillName}")
    @Operation(summary = "Get skills by skill name")
    public ResponseEntity<List<VolunteerSkillRecord>> getSkillsByName(
            @PathVariable String skillName) {
        return ResponseEntity.ok(service.getSkillsByName(skillName));
    }

    @GetMapping
    @Operation(summary = "Get all skills")
    public ResponseEntity<List<VolunteerSkillRecord>> getAllSkills() {
        return ResponseEntity.ok(service.getAllSkills());
    }
}
