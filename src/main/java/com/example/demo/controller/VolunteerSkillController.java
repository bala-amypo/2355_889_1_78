package com.example.demo.controller;

import com.example.demo.dto.VolunteerSkillRequest;
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
    public VolunteerSkillRecord addSkill(
            @RequestBody VolunteerSkillRequest request) {

        VolunteerSkillRecord record = new VolunteerSkillRecord();
        record.setVolunteerId(request.getVolunteerId());
        record.setSkillName(request.getSkillName());
        record.setSkillLevel(request.getSkillLevel());
        record.setCertified(request.isCertified());

        return service.saveSkill(record);
    }

    @GetMapping("/{volunteerId}")
    public List<VolunteerSkillRecord> getByVolunteer(
            @PathVariable Long volunteerId) {

        return service.getByVolunteer(volunteerId);
    }
}
