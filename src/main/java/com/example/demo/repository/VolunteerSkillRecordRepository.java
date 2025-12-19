package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.VolunteerSkillRecord;

public interface VolunteerSkillRecordRepository
        extends JpaRepository<VolunteerSkillRecord, Long> {

    List<VolunteerSkillRecord> findByVolunteerId(Long volunteerId);
}