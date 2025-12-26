// src/main/java/com/example/demo/controller/TaskRecordController.java
package com.example.demo.controller;

import com.example.demo.model.TaskRecord;
import com.example.demo.service.TaskRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "TaskRecordController")
public class TaskRecordController {

    @Autowired
    private TaskRecordService service;

    @PostMapping("/")
    @Operation(summary = "Create task")
    public TaskRecord create(@RequestBody TaskRecord task) {
        return service.createTask(task);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update task")
    public TaskRecord update(@PathVariable Long id, @RequestBody TaskRecord task) {
        return service.updateTask(id, task);
    }

    @GetMapping("/open")
    @Operation(summary = "List open tasks")
    public List<TaskRecord> open() {
        return service.getOpenTasks();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get task")
    public TaskRecord get(@PathVariable Long id) {
        return service.getTaskById(id);
    }

    @GetMapping("/")
    @Operation(summary = "List all")
    public List<TaskRecord> list() {
        return service.getAllTasks();
    }
}