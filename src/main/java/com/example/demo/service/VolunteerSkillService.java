package com.example.demo.service;

import com.example.demo.model.VolunteerSkillRecord;
import java.util.List;

public interface VolunteerSkillService {

    VolunteerSkillRecord saveSkill(VolunteerSkillRecord record);

    List<VolunteerSkillRecord> getSkillsByVolunteerId(Long volunteerId);

    List<VolunteerSkillRecord> getSkillsByName(String skillName);

    List<VolunteerSkillRecord> getAllSkills();
}
