// src/main/java/com/example/demo/controller/VolunteerSkillController.java
package com.example.demo.controller;

import com.example.demo.model.VolunteerSkillRecord;
import com.example.demo.service.VolunteerSkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@Tag(name = "VolunteerSkillController")
public class VolunteerSkillController {

    @Autowired
    private VolunteerSkillService service;

    @PostMapping("/")
    @Operation(summary = "Add/update skill")
    public VolunteerSkillRecord add(@RequestBody VolunteerSkillRecord skill) {
        return service.addOrUpdateSkill(skill);
    }

    @GetMapping("/volunteer/{volunteerId}")
    @Operation(summary = "Get skills")
    public List<VolunteerSkillRecord> getByVolunteer(@PathVariable Long volunteerId) {
        return service.getSkillsByVolunteer(volunteerId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get skill")
    public VolunteerSkillRecord get(@PathVariable Long id) {
        return service.getSkillById(id);
    }

    @GetMapping("/")
    @Operation(summary = "List all")
    public List<VolunteerSkillRecord> list() {
        return service.getAllSkills();
    }
}