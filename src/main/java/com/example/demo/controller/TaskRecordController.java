package com.example.demo.controller;

import com.example.demo.model.TaskRecord;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskRecordController {

    @PostMapping
    @Operation(summary = "Create task")
    public TaskRecord createTask(@RequestBody TaskRecord task) {
        return task;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update task")
    public TaskRecord updateTask(@PathVariable Long id, @RequestBody TaskRecord task) {
        return task;
    }

    @GetMapping("/open")
    @Operation(summary = "List open tasks")
    public List<TaskRecord> listOpenTasks() {
        return List.of();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get task by ID")
    public TaskRecord getTask(@PathVariable Long id) {
        return new TaskRecord();
    }

    @GetMapping
    @Operation(summary = "List all tasks")
    public List<TaskRecord> listAllTasks() {
        return List.of();
    }
}
