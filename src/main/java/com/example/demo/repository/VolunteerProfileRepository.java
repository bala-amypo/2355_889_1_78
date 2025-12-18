package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.VolunteerProfile;
Optional<VolunteerProfile> findByVolunteerId(String volunteerId);
public interface VolunteerProfileRepository
        extends JpaRepository<VolunteerProfile, Long> {
}
