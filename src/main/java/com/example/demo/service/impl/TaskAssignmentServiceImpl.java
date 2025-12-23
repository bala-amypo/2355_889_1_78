
package com.example.demo.service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.util.SkillLevelUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public TaskAssignmentRecord assignTask(Long taskId) {
        // Find task
        TaskRecord task = taskRepository.findById(taskId)
            .orElseThrow(() -> new ResourceNotFoundException("required skill level"));
        
        // Check if task is already assigned
        if (!task.getStatus().equals(TaskRecord.TaskStatus.OPEN)) {
            throw new BadRequestException("required skill level");
        }
        
        // Find AVAILABLE volunteers
        List<VolunteerProfile> availableVolunteers = volunteerRepository
            .findByAvailabilityStatus(VolunteerProfile.AvailabilityStatus.AVAILABLE);
        
        if (availableVolunteers.isEmpty()) {
            throw new ResourceNotFoundException("No AVAILABLE volunteers");
        }
        
        // Find best matching volunteer
        VolunteerProfile bestMatch = findBestMatch(task, availableVolunteers);
        
        if (bestMatch == null) {
            throw new ResourceNotFoundException("required skill level");
        }
        
        // Create assignment
        TaskAssignmentRecord assignment = new TaskAssignmentRecord();
        assignment.setTaskId(taskId);
        assignment.setVolunteerId(bestMatch.getVolunteerId());
        assignment.setStatus(TaskAssignmentRecord.AssignmentStatus.ACTIVE);
        
        // Update task status
        task.setStatus(TaskRecord.TaskStatus.ASSIGNED);
        taskRepository.save(task);
        
        return assignmentRepository.save(assignment);
    }
    
    private VolunteerProfile findBestMatch(TaskRecord task, List<VolunteerProfile> volunteers) {
        int requiredLevel = SkillLevelUtil.levelToInt(task.getRequiredSkillLevel().name());
        
        for (VolunteerProfile volunteer : volunteers) {
            List<VolunteerSkillRecord> skills = skillRepository
                .findByVolunteerId(volunteer.getVolunteerId());
            
            for (VolunteerSkillRecord skill : skills) {
                // Check if skill name matches (case-insensitive)
                if (skill.getSkillName().equalsIgnoreCase(task.getRequiredSkill())) {
                    int volunteerLevel = SkillLevelUtil.levelToInt(skill.getSkillLevel().name());
                    
                    // Check if volunteer's skill level meets or exceeds requirement
                    if (SkillLevelUtil.canMatchSkill(volunteerLevel, requiredLevel)) {
                        return volunteer;
                    }
                }
            }
        }
        
        return null;
    }
    
    @Override
    @Transactional
    public TaskAssignmentRecord updateAssignmentStatus(Long id, String status) {
        TaskAssignmentRecord assignment = assignmentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("ACTIVE assignment"));
        
        try {
            TaskAssignmentRecord.AssignmentStatus newStatus = 
                TaskAssignmentRecord.AssignmentStatus.valueOf(status.toUpperCase());
            assignment.setStatus(newStatus);
            
            // If assignment is completed or cancelled, update task status
            if (newStatus == TaskAssignmentRecord.AssignmentStatus.COMPLETED) {
                TaskRecord task = taskRepository.findById(assignment.getTaskId())
                    .orElseThrow(() -> new ResourceNotFoundException("required skill level"));
                task.setStatus(TaskRecord.TaskStatus.COMPLETED);
                taskRepository.save(task);
            } else if (newStatus == TaskAssignmentRecord.AssignmentStatus.CANCELLED) {
                TaskRecord task = taskRepository.findById(assignment.getTaskId())
                    .orElseThrow(() -> new ResourceNotFoundException("required skill level"));
                task.setStatus(TaskRecord.TaskStatus.OPEN);
                taskRepository.save(task);
            }
            
            return assignmentRepository.save(assignment);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Invalid assignment status: " + status);
        }
    }
    
    @Override
    public List<TaskAssignmentRecord> getAssignmentsByVolunteer(Long volunteerId) {
        return assignmentRepository.findByVolunteerId(volunteerId);
    }
    
    @Override
    public List<TaskAssignmentRecord> getAssignmentsByTask(Long taskId) {
        return assignmentRepository.findByTaskId(taskId);
    }
    
    @Override
    public List<TaskAssignmentRecord> getAllAssignments() {
        return assignmentRepository.findAll();
    }
    
    @Override
    public TaskAssignmentRecord getAssignmentById(Long id) {
        return assignmentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("ACTIVE assignment"));
    }
}