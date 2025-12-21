package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.example.demo.model.TaskAssignmentRecord;
import com.example.demo.repository.TaskAssignmentRecordRepository;
import com.example.demo.service.TaskAssignmentService;

@Service
public class TaskAssignmentServiceImpl implements TaskAssignmentService {

    @Autowired
    TaskAssignmentRecordRepository repo;

    public TaskAssignmentRecord assignTask(Long taskId) {
        TaskAssignmentRecord a = new TaskAssignmentRecord();
        a.setTaskId(taskId);
        a.setVolunteerId(1L); 
        return repo.save(a);
    }

    public TaskAssignmentRecord updateAssignmentStatus(Long id, String status) {
        TaskAssignmentRecord a = repo.findById(id).orElse(null);
        if (a != null) {
            a.setStatus(status);
            return repo.save(a);
        }
        return null;
    }

    public List<TaskAssignmentRecord> getAssignmentsByVolunteer(Long volunteerId) {
        return repo.findByVolunteerId(volunteerId);
    }

    public List<TaskAssignmentRecord> getAssignmentsByTask(Long taskId) {
        return repo.findByTaskId(taskId);
    }

    public List<TaskAssignmentRecord> getAllAssignments() {
        return repo.findAll();
    }
}