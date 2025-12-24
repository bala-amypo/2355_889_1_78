package com.example.demo.service.impl;

import com.example.demo.model.VolunteerSkillRecord;
import com.example.demo.repository.VolunteerSkillRepository;
import com.example.demo.service.VolunteerSkillService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VolunteerSkillServiceImpl implements VolunteerSkillService {

    private final VolunteerSkillRepository repository;

    public VolunteerSkillServiceImpl(VolunteerSkillRepository repository) {
        this.repository = repository;
    }

    @Override
    public VolunteerSkillRecord saveSkill(VolunteerSkillRecord record) {
        return repository.save(record);
    }

    @Override
    public List<VolunteerSkillRecord> getByVolunteer(Long volunteerId) {
        return repository.findByVolunteerId(volunteerId);
    }
}
