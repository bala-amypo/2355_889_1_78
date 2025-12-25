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
    private final TaskAssignmentRecordRepository assignmentRepo;
    private final TaskRecordRepository taskRepo;
    private final VolunteerProfileRepository volunteerRepo;
    private final VolunteerSkillRecordRepository skillRepo;

    public TaskAssignmentServiceImpl(TaskAssignmentRecordRepository assignmentRepo,
                                     TaskRecordRepository taskRepo,
                                     VolunteerProfileRepository volunteerRepo,
                                     VolunteerSkillRecordRepository skillRepo) {
        this.assignmentRepo = assignmentRepo;
        this.taskRepo = taskRepo;
        this.volunteerRepo = volunteerRepo;
        this.skillRepo = skillRepo;
    }

    @Override
    public TaskAssignmentRecord assignTask(Long taskId) {
        TaskRecord task = taskRepo.findById(taskId)
                .orElseThrow(() -> new BadRequestException("Task not found"));

        if (!"OPEN".equals(task.getStatus())) {
            throw new BadRequestException("Task is not OPEN");
        }

        if (assignmentRepo.existsByTaskIdAndStatus(taskId, "ACTIVE")) {
            throw new BadRequestException("Task already has an ACTIVE assignment");
        }

        List<VolunteerProfile> available = volunteerRepo.findByAvailabilityStatus("AVAILABLE");
        if (available.isEmpty()) {
            throw new BadRequestException("No AVAILABLE volunteers");
        }

        VolunteerProfile selected = null;
        int bestRank = -1;

        for (VolunteerProfile v : available) {
            List<VolunteerSkillRecord> skills = skillRepo.findByVolunteerId(v.getId());
            for (VolunteerSkillRecord s : skills) {
                if (task.getRequiredSkill().equals(s.getSkillName())) {
                    int rank = SkillLevelUtil.levelRank(s.getSkillLevel());
                    if (rank >= SkillLevelUtil.levelRank(task.getRequiredSkillLevel()) && rank > bestRank) {
                        bestRank = rank;
                        selected = v;
                    }
                }
            }
        }

        if (selected == null) {
            throw new BadRequestException("No volunteer with sufficient required skill level");
        }

        TaskAssignmentRecord assignment = new TaskAssignmentRecord();
        assignment.setTaskId(taskId);
        assignment.setVolunteerId(selected.getId());
        assignment = assignmentRepo.save(assignment);

        task.setStatus("ASSIGNED");
        taskRepo.save(task);

        return assignment;
    }

    @Override
    public List<TaskAssignmentRecord> getAssignmentsByTask(Long taskId) {
        return assignmentRepo.findByTaskId(taskId);
    }

    @Override
    public List<TaskAssignmentRecord> getAssignmentsByVolunteer(Long volunteerId) {
        return assignmentRepo.findByVolunteerId(volunteerId);
    }

    @Override
    public List<TaskAssignmentRecord> getAllAssignments() {
        return assignmentRepo.findAll();
    }
}