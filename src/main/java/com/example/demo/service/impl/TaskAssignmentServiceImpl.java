package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.TaskAssignmentService;
import com.example.demo.util.SkillLevelUtil;

import java.util.*;

public class TaskAssignmentServiceImpl implements TaskAssignmentService {

    private final TaskAssignmentRecordRepository assignmentRepo;
    private final TaskRecordRepository taskRepo;
    private final VolunteerProfileRepository profileRepo;
    private final VolunteerSkillRecordRepository skillRepo;

    public TaskAssignmentServiceImpl(TaskAssignmentRecordRepository a,
                                     TaskRecordRepository t,
                                     VolunteerProfileRepository v,
                                     VolunteerSkillRecordRepository s) {
        this.assignmentRepo = a;
        this.taskRepo = t;
        this.profileRepo = v;
        this.skillRepo = s;
    }

    @Override
    public TaskAssignmentRecord assignTask(Long taskId) {

        TaskRecord task = taskRepo.findById(taskId)
                .orElseThrow(() -> new BadRequestException("Task not found"));

        if (assignmentRepo.existsByTaskIdAndStatus(taskId, "ACTIVE"))
            throw new BadRequestException("ACTIVE assignment already exists");

        List<VolunteerProfile> volunteers =
                profileRepo.findByAvailabilityStatus("AVAILABLE");

        if (volunteers.isEmpty())
            throw new BadRequestException("No AVAILABLE volunteers");

        for (VolunteerProfile v : volunteers) {
            List<VolunteerSkillRecord> skills =
                    skillRepo.findByVolunteerId(v.getId());

            for (VolunteerSkillRecord s : skills) {
                if (s.getSkillName().equals(task.getRequiredSkill()) &&
                    SkillLevelUtil.levelRank(s.getSkillLevel()) >=
                    SkillLevelUtil.levelRank(task.getRequiredSkillLevel())) {

                    TaskAssignmentRecord rec = new TaskAssignmentRecord();
                    rec.setTaskId(taskId);
                    rec.setVolunteerId(v.getId());

                    task.setStatus("ASSIGNED");
                    taskRepo.save(task);

                    return assignmentRepo.save(rec);
                }
            }
        }
        throw new BadRequestException("required skill level not met");
    }

    public List<TaskAssignmentRecord> getAssignmentsByTask(Long id) {
        return assignmentRepo.findByTaskId(id);
    }

    public List<TaskAssignmentRecord> getAssignmentsByVolunteer(Long id) {
        return assignmentRepo.findByVolunteerId(id);
    }

    public List<TaskAssignmentRecord> getAllAssignments() {
        return assignmentRepo.findAll();
    }
}
