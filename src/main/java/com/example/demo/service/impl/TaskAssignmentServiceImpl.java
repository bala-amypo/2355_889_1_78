package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.TaskAssignmentService;
import com.example.demo.util.SkillLevelUtil;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskAssignmentServiceImpl implements TaskAssignmentService {
    
    private final TaskAssignmentRecordRepository assignmentRepository;
    private final TaskRecordRepository taskRepository;
    private final VolunteerProfileRepository volunteerRepository;
    private final VolunteerSkillRecordRepository skillRepository;
    
    public TaskAssignmentServiceImpl(
            TaskAssignmentRecordRepository assignmentRepository,
            TaskRecordRepository taskRepository,
            VolunteerProfileRepository volunteerRepository,
            VolunteerSkillRecordRepository skillRepository) {
        this.assignmentRepository = assignmentRepository;
        this.taskRepository = taskRepository;
        this.volunteerRepository = volunteerRepository;
        this.skillRepository = skillRepository;
    }
    
    @Override
    public TaskAssignmentRecord assignTask(Long taskId) {
        TaskRecord task = taskRepository.findById(taskId)
            .orElseThrow(() -> new BadRequestException("Task not found"));
        
        if (assignmentRepository.existsByTaskIdAndStatus(taskId, "ACTIVE")) {
            throw new BadRequestException("Task already has an ACTIVE assignment");
        }
        
        List<VolunteerProfile> availableVolunteers = 
            volunteerRepository.findByAvailabilityStatus("AVAILABLE");
        
        if (availableVolunteers.isEmpty()) {
            throw new BadRequestException("No AVAILABLE volunteers found");
        }
        
        VolunteerProfile bestVolunteer = findBestVolunteer(
            availableVolunteers, task.getRequiredSkill(), task.getRequiredSkillLevel());
        
        if (bestVolunteer == null) {
            throw new BadRequestException("No volunteer meets the required skill level");
        }
        
        TaskAssignmentRecord assignment = new TaskAssignmentRecord();
        assignment.setTaskId(taskId);
        assignment.setVolunteerId(bestVolunteer.getId());
        assignment.setStatus("ACTIVE");
        
        task.setStatus("ASSIGNED");
        taskRepository.save(task);
        
        return assignmentRepository.save(assignment);
    }
    
    private VolunteerProfile findBestVolunteer(
            List<VolunteerProfile> volunteers, String requiredSkill, String requiredLevel) {
        
        int requiredRank = SkillLevelUtil.levelRank(requiredLevel);
        
        for (VolunteerProfile volunteer : volunteers) {
            List<VolunteerSkillRecord> skills = 
                skillRepository.findByVolunteerId(volunteer.getId());
            
            for (VolunteerSkillRecord skill : skills) {
                if (skill.getSkillName().equals(requiredSkill)) {
                    int volunteerRank = SkillLevelUtil.levelRank(skill.getSkillLevel());
                    if (volunteerRank >= requiredRank) {
                        return volunteer;
                    }
                }
            }
        }
        
        return null;
    }
    
    @Override
    public List<TaskAssignmentRecord> getAssignmentsByTask(Long taskId) {
        return assignmentRepository.findByTaskId(taskId);
    }
    
    @Override
    public List<TaskAssignmentRecord> getAssignmentsByVolunteer(Long volunteerId) {
        return assignmentRepository.findByVolunteerId(volunteerId);
    }
    
    @Override
    public List<TaskAssignmentRecord> getAllAssignments() {
        return assignmentRepository.findAll();
    }
}

