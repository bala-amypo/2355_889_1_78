
package com.example.demo.service;

import com.example.demo.dto.VolunteerSkillRequest;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.VolunteerSkillRecord;
import com.example.demo.repository.VolunteerSkillRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class VolunteerSkillServiceImpl implements VolunteerSkillService {
    
    private final VolunteerSkillRecordRepository repository;
    
    public VolunteerSkillServiceImpl(VolunteerSkillRecordRepository repository) {
        this.repository = repository;
    }
    
    @Override
    @Transactional
    public VolunteerSkillRecord addOrUpdateSkill(VolunteerSkillRequest request) {
        try {
            VolunteerSkillRecord skill = new VolunteerSkillRecord();
            skill.setVolunteerId(request.getVolunteerId());
            skill.setSkillName(request.getSkillName());
            skill.setSkillLevel(
                VolunteerSkillRecord.SkillLevel.valueOf(request.getSkillLevel().toUpperCase())
            );
            skill.setCertified(request.getCertified() != null ? request.getCertified() : false);
            
            return repository.save(skill);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid skill level: " + request.getSkillLevel());
        }
    }
    
    @Override
    public List<VolunteerSkillRecord> getSkillsByVolunteerId(Long volunteerId) {
        List<VolunteerSkillRecord> skills = repository.findByVolunteerId(volunteerId);
        if (skills.isEmpty()) {
            throw new ResourceNotFoundException("No skills found for volunteer ID: " + volunteerId);
        }
        return skills;
    }
    
    @Override
    public VolunteerSkillRecord getSkillById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Skill not found with ID: " + id));
    }
    
    @Override
    public List<VolunteerSkillRecord> getAllSkills() {
        return repository.findAll();
    }
    
    @Override
    public List<VolunteerSkillRecord> getSkillsByName(String skillName) {
        return repository.findBySkillName(skillName);
    }
}