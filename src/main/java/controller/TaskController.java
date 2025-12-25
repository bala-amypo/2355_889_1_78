package com.example.demo.controller;

import com.example.demo.model.TaskRecord;
import com.example.demo.service.TaskRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    
    private final TaskRecordService taskService;
    
    public TaskController(TaskRecordService taskService) {
        this.taskService = taskService;
    }
    
    @PostMapping
    public ResponseEntity<TaskRecord> createTask(@RequestBody TaskRecord task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }
    
    @GetMapping
    public ResponseEntity<List<TaskRecord>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
    
    @GetMapping("/open")
    public ResponseEntity<List<TaskRecord>> getOpenTasks() {
        return ResponseEntity.ok(taskService.getOpenTasks());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TaskRecord> updateTask(
            @PathVariable Long id,
            @RequestBody TaskRecord task) {
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }
}
