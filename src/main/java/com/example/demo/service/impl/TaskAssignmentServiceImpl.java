package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TaskAssignmentRecord;
import com.example.demo.repository.TaskAssignmentRecordRepository;
import com.example.demo.service.TaskAssignmentService;

@Service
public class TaskAssignmentServiceImpl implements TaskAssignmentService {

    @Autowired
    private TaskAssignmentRecordRepository repository;

    public TaskAssignmentRecord assignTask(TaskAssignmentRecord assignment) {
        return repository.save(assignment);
    }

    public TaskAssignmentRecord getAssignmentById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<TaskAssignmentRecord> getAssignmentsByVolunteer(Long volunteerId) {
        return repository.findByVolunteerId(volunteerId);
    }

    public List<TaskAssignmentRecord> getAssignmentsByTask(Long taskId) {
        return repository.findByTaskId(taskId);
    }
}