package com.example.demo.service;

import com.example.demo.model.*;
import java.util.List;

public interface VolunteerProfileService {
    VolunteerProfile createVolunteer(VolunteerProfile v);
    VolunteerProfile getVolunteerById(Long id);
    List<VolunteerProfile> getAllVolunteers();
}
