package com.example.demo.service;

import com.example.demo.dto.VolunteerSkillRequest;
import com.example.demo.model.VolunteerSkillRecord;
import java.util.List;

public interface VolunteerSkillService {
    VolunteerSkillRecord addOrUpdateSkill(VolunteerSkillRequest request);
    List<VolunteerSkillRecord> getSkillsByVolunteerId(Long volunteerId);
    VolunteerSkillRecord getSkillById(Long id);
    List<VolunteerSkillRecord> getAllSkills();
    List<VolunteerSkillRecord> getSkillsByName(String skillName);
}