package com.example.demo.service.impl;

import com.example.demo.dto.VolunteerSkillRequest;
import com.example.demo.model.VolunteerSkillRecord;
import com.example.demo.repository.VolunteerSkillRecordRepository;
import com.example.demo.service.VolunteerSkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolunteerSkillServiceImpl implements VolunteerSkillService {

    private final VolunteerSkillRecordRepository repository;

    public VolunteerSkillServiceImpl(VolunteerSkillRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public VolunteerSkillRecord addOrUpdateSkill(VolunteerSkillRequest request) {
        VolunteerSkillRecord skill = new VolunteerSkillRecord();
        skill.setVolunteerId(request.getVolunteerId());
        skill.setSkillName(request.getSkillName());
        skill.setSkillLevel(
                VolunteerSkillRecord.SkillLevel.valueOf(
                        request.getSkillLevel().toUpperCase()
                )
        );
        skill.setCertified(request.getCertified());

        return repository.save(skill);
    }

    @Override
    public List<VolunteerSkillRecord> getSkillsByVolunteerId(Long volunteerId) {
        return repository.findByVolunteerId(volunteerId);
    }

    @Override
    public List<VolunteerSkillRecord> getSkillsByName(String skillName) {
        return repository.findBySkillName(skillName);
    }

    @Override
    public List<VolunteerSkillRecord> getAllSkills() {
        return repository.findAll();
    }
}
