package com.example.demo.service.impl;

import com.example.demo.service.VolunteerSkillService;
import com.example.demo.repository.VolunteerSkillRecordRepository;
import com.example.demo.model.VolunteerSkillRecord;
import java.util.List;

public class VolunteerSkillServiceImpl implements VolunteerSkillService {

    private final VolunteerSkillRecordRepository repo;

    public VolunteerSkillServiceImpl(VolunteerSkillRecordRepository repo) {
        this.repo = repo;
    }

    public VolunteerSkillRecord addOrUpdateSkill(VolunteerSkillRecord s) {
        return repo.save(s);
    }

    public List<VolunteerSkillRecord> getSkillsByVolunteer(Long id) {
        return repo.findByVolunteerId(id);
    }
}
