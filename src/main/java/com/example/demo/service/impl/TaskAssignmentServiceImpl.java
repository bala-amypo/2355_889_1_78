package com.example.demo.service.impl;

import com.example.demo.model.TaskAssignmentRecord;
import com.example.demo.model.TaskRecord;
import com.example.demo.model.VolunteerProfile;
import com.example.demo.repository.TaskAssignmentRecordRepository;
import com.example.demo.repository.TaskRecordRepository;
import com.example.demo.repository.VolunteerProfileRepository;
import com.example.demo.service.TaskAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskAssignmentServiceImpl implements TaskAssignmentService {

    @Autowired
    private TaskAssignmentRecordRepository assignmentRepo;

    @Autowired
    private TaskRecordRepository taskRepo;

    @Autowired
    private VolunteerProfileRepository volunteerRepo;

    @Override
    public TaskAssignmentRecord assignTask(Long taskId, Long volunteerId) {
        TaskRecord task = taskRepo.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        VolunteerProfile volunteer = volunteerRepo.findById(volunteerId)
                .orElseThrow(() -> new RuntimeException("Volunteer not found"));

        // Check if already assigned
        boolean alreadyAssigned = assignmentRepo.existsByTaskIdAndVolunteerId(taskId, volunteerId);
        if (alreadyAssigned) throw new RuntimeException("Task already assigned to this volunteer");

        TaskAssignmentRecord assignment = new TaskAssignmentRecord();
        assignment.setTask(task);
        assignment.setVolunteer(volunteer);
        assignment.setStatus("ASSIGNED");

        return assignmentRepo.save(assignment);
    }

    @Override
    public TaskAssignmentRecord updateAssignmentStatus(Long id, String status) {
        TaskAssignmentRecord assignment = assignmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
        assignment.setStatus(status);
        return assignmentRepo.save(assignment);
    }

    @Override
    public List<TaskAssignmentRecord> getAssignmentsByVolunteer(Long volunteerId) {
        return assignmentRepo.findByVolunteerId(volunteerId);
    }

    @Override
    public List<TaskAssignmentRecord> getAssignmentsByTask(Long taskId) {
        return assignmentRepo.findByTaskId(taskId);
    }

    @Override
    public List<TaskAssignmentRecord> getAllAssignments() {
        return assignmentRepo.findAll();
    }
}
