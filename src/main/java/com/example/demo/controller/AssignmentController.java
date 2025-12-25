package com.example.demo.controller;

import com.example.demo.model.TaskAssignmentRecord;
import com.example.demo.service.TaskAssignmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    private final TaskAssignmentService service;

    public AssignmentController(TaskAssignmentService service) {
        this.service = service;
    }

    @PostMapping("/assign/{taskId}")
    public TaskAssignmentRecord assign(@PathVariable Long taskId) {
        return service.assignTask(taskId);
    }

    @GetMapping
    public java.util.List<TaskAssignmentRecord> getAll() {
        return service.getAllAssignments();
    }
}