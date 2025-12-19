package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.VolunteerSkillRecord;
import com.example.demo.service.VolunteerSkillService;

@RestController
@RequestMapping("/api/skills")
@CrossOrigin
public class VolunteerSkillController {

    @Autowired
    private VolunteerSkillService service;

    // Add or Update Skill
    @PostMapping
    public VolunteerSkillRecord addSkill(@RequestBody VolunteerSkillRecord skill) {
        return service.addOrUpdateSkill(skill);
    }

    // Get skill by ID
    @GetMapping("/{id}")
    public VolunteerSkillRecord getSkill(@PathVariable Long id) {
        return service.getSkillById(id);
    }

    // Get skills by volunteer
    @GetMapping("/volunteer/{volunteerId}")
    public List<VolunteerSkillRecord> getByVolunteer(@PathVariable Long volunteerId) {
        return service.getSkillsByVolunteer(volunteerId);
    }

    // Get all skills
    @GetMapping
    public List<VolunteerSkillRecord> getAll() {
        return service.getAllSkills();
    }
}