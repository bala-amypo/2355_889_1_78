package com.example.demo.service;

import com.example.demo.model.*;
import java.util.List;
public interface VolunteerSkillService {
    VolunteerSkillRecord addOrUpdateSkill(VolunteerSkillRecord s);
    List<VolunteerSkillRecord> getSkillsByVolunteer(Long id);
}

