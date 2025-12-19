package com.example.demo.service;

import java.util.List;
import com.example.demo.model.VolunteerSkillRecord;

public interface VolunteerSkillService {

    VolunteerSkillRecord addOrUpdateSkill(VolunteerSkillRecord skill);

    VolunteerSkillRecord getSkillById(Long id);

    List<VolunteerSkillRecord> getSkillsByVolunteer(Long volunteerId);

    List<VolunteerSkillRecord> getAllSkills();
}