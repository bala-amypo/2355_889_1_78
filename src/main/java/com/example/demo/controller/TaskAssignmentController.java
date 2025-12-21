package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.example.demo.model.TaskAssignmentRecord;
import com.example.demo.service.TaskAssignmentService;

@RestController
@RequestMapping("/api/assignments")
public class TaskAssignmentController {

    @Autowired
    TaskAssignmentService service;

    @PostMapping("/assign/{taskId}")
    public TaskAssignmentRecord assign(@PathVariable Long taskId) {
        return service.assignTask(taskId);
    }

    @PutMapping("/{id}/status")
    public TaskAssignmentRecord updateStatus(@PathVariable Long id,
                                             @RequestParam String status) {
        return service.updateAssignmentStatus(id, status);
    }

    @GetMapping("/volunteer/{volunteerId}")
    public List<TaskAssignmentRecord> byVolunteer(@PathVariable Long volunteerId) {
        return service.getAssignmentsByVolunteer(volunteerId);
    }

    @GetMapping("/task/{taskId}")
    public List<TaskAssignmentRecord> byTask(@PathVariable Long taskId) {
        return service.getAssignmentsByTask(taskId);
    }

    @GetMapping
    public List<TaskAssignmentRecord> all() {
        return service.getAllAssignments();
    }
}