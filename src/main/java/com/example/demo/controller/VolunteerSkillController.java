package com.example.demo.controller;

import com.example.demo.model.VolunteerSkillRecord;
import com.example.demo.service.VolunteerSkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class VolunteerSkillController {

    private final VolunteerSkillService service;

    public VolunteerSkillController(VolunteerSkillService service) {
        this.service = service;
    }

    @PostMapping
    public VolunteerSkillRecord save(@RequestBody VolunteerSkillRecord skill) {
        return service.addOrUpdateSkill(skill);
    }

    @GetMapping
    public List<VolunteerSkillRecord> all() {
        return service.getAllSkills();
    }

    @GetMapping("/{id}")
    public VolunteerSkillRecord get(@PathVariable Long id) {
        return service.getSkillById(id);
    }
}
