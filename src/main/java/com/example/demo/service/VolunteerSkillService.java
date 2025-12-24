package com.example.demo.service;

import com.example.demo.model.VolunteerSkillRecord;
import java.util.List;

public interface VolunteerSkillService {

    VolunteerSkillRecord saveSkill(VolunteerSkillRecord record);

    List<VolunteerSkillRecord> getByVolunteer(Long volunteerId);
}
