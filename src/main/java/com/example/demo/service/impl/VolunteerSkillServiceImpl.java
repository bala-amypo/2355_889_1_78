package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.example.demo.model.VolunteerSkillRecord;
import com.example.demo.repository.VolunteerSkillRecordRepository;
import com.example.demo.service.VolunteerSkillService;

@Service
public class VolunteerSkillServiceImpl implements VolunteerSkillService {

    @Autowired
    VolunteerSkillRecordRepository repo;

    @Override
    public VolunteerSkillRecord addOrUpdateSkill(VolunteerSkillRecord skill) {
        return repo.save(skill);
    }

    @Override
    public List<VolunteerSkillRecord> getSkillsByVolunteer(Long volunteerId) {
        return repo.findByVolunteerId(volunteerId);
    }

    @Override
    public VolunteerSkillRecord getSkillById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<VolunteerSkillRecord> getAllSkills() {
        return repo.findAll();
    }
}