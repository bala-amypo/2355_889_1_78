package com.example.demo.controller;

import com.example.demo.model.TaskAssignmentRecord;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class TaskAssignmentController {

    @PostMapping("/assign/{taskId}")
    @Operation(summary = "Auto-assign task")
    public TaskAssignment autoAssign(@PathVariable Long taskId) {
        return new TaskAssignment();
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update assignment status")
    public TaskAssignment updateStatus(@PathVariable Long id, @RequestParam String status) {
        return new TaskAssignment();
    }

    @GetMapping("/volunteer/{volunteerId}")
    @Operation(summary = "Get assignments by volunteer")
    public List<TaskAssignment> getByVolunteer(@PathVariable Long volunteerId) {
        return List.of();
    }

    @GetMapping("/task/{taskId}")
    @Operation(summary = "Get assignments by task")
    public TaskAssignment getByTask(@PathVariable Long taskId) {
        return new TaskAssignment();
    }

    @GetMapping
    @Operation(summary = "List all assignments")
    public List<TaskAssignment> listAllAssignments() {
        return List.of();
    }
}
