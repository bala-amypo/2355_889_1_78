package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.VolunteerProfile;

public interface VolunteerProfileRepository extends JpaRepository<VolunteerProfile, Long> {

    Optional<VolunteerProfile> findByVolunteerId(String volunteerId);
}
