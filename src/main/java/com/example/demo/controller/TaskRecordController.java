package com.example.demo.controller;

import com.example.demo.model.TaskRecord;
import com.example.demo.service.TaskRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskRecordController {

    private final TaskRecordService service;

    public TaskRecordController(TaskRecordService service) {
        this.service = service;
    }

    @PostMapping
    public TaskRecord create(@RequestBody TaskRecord task) {
        return service.createTask(task);
    }

    @PutMapping("/{id}")
    public TaskRecord update(
            @PathVariable Long id,
            @RequestBody TaskRecord task) {

        return service.updateTask(id, task);
    }

    @GetMapping
    public List<TaskRecord> all() {
        return service.getAllTasks();
    }

    @GetMapping("/open")
    public List<TaskRecord> openTasks() {
        return service.getOpenTasks();
    }
}
