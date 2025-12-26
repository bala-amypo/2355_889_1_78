// src/main/java/com/example/demo/controller/TaskAssignmentController.java
package com.example.demo.controller;

import com.example.demo.model.TaskAssignmentRecord;
import com.example.demo.service.TaskAssignmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/assignments")
@Tag(name = "TaskAssignmentController")
public class TaskAssignmentController {

    @Autowired
    private TaskAssignmentService service;

    @PostMapping("/assign/{taskId}")
    @Operation(summary = "Auto-assign")
    public TaskAssignmentRecord assign(@PathVariable Long taskId) {
        return service.assignTask(taskId);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update status")
    public void updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        service.updateStatus(id, body.get("status"));
    }

    @GetMapping("/volunteer/{volunteerId}")
    @Operation(summary = "Get by volunteer")
    public List<TaskAssignmentRecord> byVolunteer(@PathVariable Long volunteerId) {
        return service.getAssignmentsByVolunteer(volunteerId);
    }

    @GetMapping("/task/{taskId}")
    @Operation(summary = "Get by task")
    public List<TaskAssignmentRecord> byTask(@PathVariable Long taskId) {
        return service.getAssignmentsByTask(taskId);
    }

    @GetMapping("/")
    @Operation(summary = "List all")
    public List<TaskAssignmentRecord> list() {
        return service.getAllAssignments();
    }
}