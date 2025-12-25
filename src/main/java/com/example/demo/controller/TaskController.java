package com.example.demo.controller;

import com.example.demo.model.TaskRecord;
import com.example.demo.service.TaskRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskRecordService service;

    public TaskController(TaskRecordService service) {
        this.service = service;
    }

    @PostMapping
    public TaskRecord create(@RequestBody TaskRecord task) {
        return service.createTask(task);
    }

    @GetMapping("/open")
    public List<TaskRecord> getOpen() {
        return service.getOpenTasks();
    }

    @GetMapping
    public List<TaskRecord> getAll() {
        return service.getAllTasks();
    }
}