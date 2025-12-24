package com.example.demo.service.impl;

import com.example.demo.model.VolunteerSkillRecord;
import com.example.demo.repository.VolunteerSkillRecordRepository;
import com.example.demo.service.VolunteerSkillService;

import java.util.List;

public class VolunteerSkillServiceImpl implements VolunteerSkillService {

    private final VolunteerSkillRecordRepository repo;

    public VolunteerSkillServiceImpl(VolunteerSkillRecordRepository repo) {
        this.repo = repo;
    }

    public VolunteerSkillRecord addOrUpdateSkill(VolunteerSkillRecord skill) {
        return repo.save(skill);
    }

    public List<VolunteerSkillRecord> getSkillsByVolunteer(Long id) {
        return repo.findByVolunteerId(id);
    }
}
