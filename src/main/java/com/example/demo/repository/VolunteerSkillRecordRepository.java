package com.example.demo.repository;

import com.example.demo.model.VolunteerSkillRecord;
import java.util.List;

public interface VolunteerSkillRecordRepository {

    VolunteerSkillRecord save(VolunteerSkillRecord skill);

    List<VolunteerSkillRecord> findByVolunteerId(Long volunteerId);

    List<VolunteerSkillRecord> findBySkillName(String skillName);

    List<VolunteerSkillRecord> findBySkillNameAndSkillLevel(
            String skillName,
            String skillLevel
    );
}
