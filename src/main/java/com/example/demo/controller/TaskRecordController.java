package com.example.demo.controller;

import com.example.demo.dto.TaskRecordRequest;
import com.example.demo.model.TaskRecord;
import com.example.demo.service.TaskRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Task Records", description = "Task management")
public class TaskRecordController {
    
    private final TaskRecordService service;
    
    public TaskRecordController(TaskRecordService service) {
        this.service = service;
    }
    
    @PostMapping
    @Operation(summary = "Create task")
    public ResponseEntity<TaskRecord> createTask(@RequestBody TaskRecordRequest request) {
        return ResponseEntity.ok(service.createTask(request));
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update task")
    public ResponseEntity<TaskRecord> updateTask(@PathVariable Long id, @RequestBody TaskRecordRequest request) {
        return ResponseEntity.ok(service.updateTask(id, request));
    }
    
    @GetMapping("/open")
    @Operation(summary = "Get all open tasks")
    public ResponseEntity<List<TaskRecord>> getOpenTasks() {
        return ResponseEntity.ok(service.getOpenTasks());
    }
    
    @GetMapping("/{taskId}")
    @Operation(summary = "Get task by task code")
    public ResponseEntity<TaskRecord> getTaskByCode(@PathVariable String taskId) {
        return ResponseEntity.ok(service.getTaskByCode(taskId));
    }
    
    @GetMapping
    @Operation(summary = "Get all tasks")
    public ResponseEntity<List<TaskRecord>> getAllTasks() {
        return ResponseEntity.ok(service.getAllTasks());
    }
}
