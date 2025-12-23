package com.example.demo.repository;

import com.example.demo.model.VolunteerProfile;
import com.example.demo.model.VolunteerProfile.AvailabilityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VolunteerProfileRepository extends JpaRepository<VolunteerProfile, Long> {
    Optional<VolunteerProfile> findByVolunteerId(Long volunteerId);
    List<VolunteerProfile> findByAvailabilityStatus(AvailabilityStatus status);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByVolunteerId(Long volunteerId);
}
