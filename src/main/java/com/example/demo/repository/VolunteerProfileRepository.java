package com.example.demo.repository;

import com.example.demo.model.VolunteerProfile;
import java.util.*;

public interface VolunteerProfileRepository {

    boolean existsByVolunteerId(String volunteerId);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    Optional<VolunteerProfile> findById(Long id);

    Optional<VolunteerProfile> findByVolunteerId(String volunteerId);

    List<VolunteerProfile> findByAvailabilityStatus(String status);

    List<VolunteerProfile> findAll();

    VolunteerProfile save(VolunteerProfile profile);
}
