package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/skills")
@Tag(name = "Volunteer Skill Controller")
public class VolunteerSkillController {

    @PostMapping
    public String addOrUpdateSkill(@RequestBody Object skill) {
        return "Skill added/updated";
    }

    @GetMapping("/volunteer/{volunteerId}")
    public String getSkillsByVolunteer(@PathVariable Long volunteerId) {
        return "Skills for volunteer " + volunteerId;
    }

    @GetMapping("/{id}")
    public String getSkill(@PathVariable Long id) {
        return "Get skill " + id;
    }

    @GetMapping
    public String listSkills() {
        return "List all skills";
    }
}
