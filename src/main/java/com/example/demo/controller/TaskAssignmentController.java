package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.TaskAssignmentRecord;
import com.example.demo.service.TaskAssignmentService;

@RestController
@RequestMapping("/api/assignments")
@CrossOrigin
public class TaskAssignmentController {

    @Autowired
    private TaskAssignmentService service;

    @PostMapping
    public TaskAssignmentRecord assignTask(@RequestBody TaskAssignmentRecord assignment) {
        return service.assignTask(assignment);
    }

    @GetMapping("/{id}")
    public TaskAssignmentRecord getAssignment(@PathVariable Long id) {
        return service.getAssignmentById(id);
    }

    @GetMapping("/volunteer/{volunteerId}")
    public List<TaskAssignmentRecord> getByVolunteer(@PathVariable Long volunteerId) {
        return service.getAssignmentsByVolunteer(volunteerId);
    }

    @GetMapping("/task/{taskId}")
    public List<TaskAssignmentRecord> getByTask(@PathVariable Long taskId) {
        return service.getAssignmentsByTask(taskId);
    }
}