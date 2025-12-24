package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface VolunteerProfileRepository extends JpaRepository<VolunteerProfile, Long> {
    boolean existsByVolunteerId(String volunteerId);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    Optional<VolunteerProfile> findByVolunteerId(String volunteerId);
    List<VolunteerProfile> findByAvailabilityStatus(String status);
}
