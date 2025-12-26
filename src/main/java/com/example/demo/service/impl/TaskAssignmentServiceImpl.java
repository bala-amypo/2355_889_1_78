// src/main/java/com/example/demo/service/impl/TaskAssignmentServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.TaskAssignmentRecord;
import com.example.demo.model.TaskRecord;
import com.example.demo.model.VolunteerProfile;
import com.example.demo.model.VolunteerSkillRecord;
import com.example.demo.repository.TaskAssignmentRecordRepository;
import com.example.demo.repository.TaskRecordRepository;
import com.example.demo.repository.VolunteerProfileRepository;
import com.example.demo.repository.VolunteerSkillRecordRepository;
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

    public TaskAssignmentServiceImpl(TaskAssignmentRecordRepository assignmentRepository,
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
        TaskRecord task = taskRepository.findById(taskId).orElseThrow();
        if (!"OPEN".equals(task.getStatus())) {
            throw new BadRequestException("Task is not OPEN");
        }
        if (assignmentRepository.existsByTaskIdAndStatus(taskId, "ACTIVE")) {
            throw new BadRequestException("Task already has an ACTIVE assignment");
        }
        List<VolunteerProfile> availableVolunteers = volunteerRepository.findByAvailabilityStatus("AVAILABLE");
        if (availableVolunteers.isEmpty()) {
            throw new BadRequestException("No AVAILABLE volunteers found");
        }
        VolunteerProfile selected = null;
        for (VolunteerProfile v : availableVolunteers) {
            List<VolunteerSkillRecord> skills = skillRepository.findByVolunteerId(v.getId());
            for (VolunteerSkillRecord s : skills) {
                if (s.getSkillName().equals(task.getRequiredSkill()) &&
                        SkillLevelUtil.levelRank(s.getSkillLevel()) >= SkillLevelUtil.levelRank(task.getRequiredSkillLevel())) {
                    selected = v;
                    break;
                }
            }
            if (selected != null) break;
        }
        if (selected == null) {
            throw new BadRequestException("No suitable volunteers with the required skill level");
        }
        TaskAssignmentRecord assignment = new TaskAssignmentRecord();
        assignment.setTaskId(taskId);
        assignment.setVolunteerId(selected.getId());
        assignmentRepository.save(assignment);
        task.setStatus("ASSIGNED");
        taskRepository.save(task);
        return assignment;
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

    @Override
    public void updateStatus(Long id, String status) {
        TaskAssignmentRecord assignment = assignmentRepository.findById(id).orElseThrow();
        assignment.setStatus(status);
        assignmentRepository.save(assignment);
    }
}