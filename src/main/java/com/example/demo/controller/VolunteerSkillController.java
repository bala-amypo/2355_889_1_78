package com.example.demo.controller;

import com.example.demo.model.VolunteerSkillRecord;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class VolunteerSkillController {

    @PostMapping
    @Operation(summary = "Add or update skill")
    public VolunteerSkill addOrUpdateSkill(@RequestBody VolunteerSkillRecord skill) {
        return skill;
    }

    @GetMapping("/volunteer/{volunteerId}")
    @Operation(summary = "Get skills by volunteer")
    public List<VolunteerSkill> getSkills(@PathVariable Long volunteerId) {
        return List.of();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get skill by ID")
    public VolunteerSkill getSkill(@PathVariable Long id) {
        return new VolunteerSkill();
    }

    @GetMapping
    @Operation(summary = "List all skills")
    public List<VolunteerSkill> listAllSkills() {
        return List.of();
    }
}
