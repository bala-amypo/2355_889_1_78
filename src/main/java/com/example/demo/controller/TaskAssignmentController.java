package com.example.demo.controller;

import com.example.demo.dto.AssignmentStatusRequest;
import com.example.demo.model.TaskAssignmentRecord;
import com.example.demo.service.TaskAssignmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Task Assignments", description = "Task assignment operations")
public class TaskAssignmentController {
    
    private final TaskAssignmentService service;
    
    public TaskAssignmentController(TaskAssignmentService service) {
        this.service = service;
    }
    
    @PostMapping("/assign/{taskId}")
    @Operation(summary = "Auto-assign task to best matching volunteer")
    public ResponseEntity<TaskAssignmentRecord> assignTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(service.assignTask(taskId));
    }
    
    @PutMapping("/{id}/status")
    @Operation(summary = "Update assignment status")
    public ResponseEntity<TaskAssignmentRecord> updateStatus(
            @PathVariable Long id, 
            @RequestBody AssignmentStatusRequest request) {
        return ResponseEntity.ok(service.updateAssignmentStatus(id, request.getStatus()));
    }
    
    @GetMapping("/volunteer/{volunteerId}")
    @Operation(summary = "Get assignments by volunteer")
    public ResponseEntity<List<TaskAssignmentRecord>> getByVolunteer(@PathVariable Long volunteerId) {
        return ResponseEntity.ok(service.getAssignmentsByVolunteer(volunteerId));
    }
    
    @GetMapping("/task/{taskId}")
    @Operation(summary = "Get assignments by task")
    public ResponseEntity<List<TaskAssignmentRecord>> getByTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(service.getAssignmentsByTask(taskId));
    }
    
    @GetMapping
    @Operation(summary = "Get all assignments")
    public ResponseEntity<List<TaskAssignmentRecord>> getAllAssignments() {
        return ResponseEntity.ok(service.getAllAssignments());
    }
}