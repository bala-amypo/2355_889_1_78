
package com.example.demo.service;

import com.example.demo.dto.VolunteerProfileRequest;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.VolunteerProfile;
import com.example.demo.repository.VolunteerProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class VolunteerProfileServiceImpl implements VolunteerProfileService {
    
    private final VolunteerProfileRepository repository;
    
    public VolunteerProfileServiceImpl(VolunteerProfileRepository repository) {
        this.repository = repository;
    }
    
    @Override
    @Transactional
    public VolunteerProfile createVolunteer(VolunteerProfileRequest request) {
        // Check for duplicate volunteerId
        if (repository.existsByVolunteerId(request.getVolunteerId())) {
            throw new BadRequestException("Email already exists");
        }
        
        // Check for duplicate email
        if (repository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
        
        // Check for duplicate phone
        if (repository.existsByPhone(request.getPhone())) {
            throw new BadRequestException("Email already exists");
        }
        
        VolunteerProfile profile = new VolunteerProfile();
        profile.setVolunteerId(request.getVolunteerId());
        profile.setFullName(request.getFullName());
        profile.setEmail(request.getEmail());
        profile.setPhone(request.getPhone());
        profile.setAvailabilityStatus(VolunteerProfile.AvailabilityStatus.AVAILABLE);
        
        return repository.save(profile);
    }
    
    @Override
    public List<VolunteerProfile> getAllVolunteers() {
        return repository.findAll();
    }
    
    @Override
    public VolunteerProfile getVolunteerById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No AVAILABLE volunteers"));
    }
    
    @Override
    public VolunteerProfile findByVolunteerId(Long volunteerId) {
        return repository.findByVolunteerId(volunteerId)
            .orElseThrow(() -> new ResourceNotFoundException("No AVAILABLE volunteers"));
    }
    
    @Override
    public List<VolunteerProfile> getAvailableVolunteers() {
        List<VolunteerProfile> volunteers = repository.findByAvailabilityStatus(
            VolunteerProfile.AvailabilityStatus.AVAILABLE);
        
        if (volunteers.isEmpty()) {
            throw new ResourceNotFoundException("No AVAILABLE volunteers");
        }
        
        return volunteers;
    }
    
    @Override
    @Transactional
    public VolunteerProfile updateAvailability(Long id, String status) {
        VolunteerProfile profile = repository.findByVolunteerId(id)
            .orElseThrow(() -> new ResourceNotFoundException("No AVAILABLE volunteers"));
        
        try {
            VolunteerProfile.AvailabilityStatus newStatus = 
                VolunteerProfile.AvailabilityStatus.valueOf(status.toUpperCase());
            profile.setAvailabilityStatus(newStatus);
            return repository.save(profile);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid availability status: " + status);
        }
    }
}