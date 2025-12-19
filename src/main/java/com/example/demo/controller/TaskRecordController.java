package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.TaskRecord;
import com.example.demo.service.TaskRecordService;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin
public class TaskRecordController {

    @Autowired
    private TaskRecordService service;

    @PostMapping
    public TaskRecord createTask(@RequestBody TaskRecord task) {
        return service.createTask(task);
    }

    @GetMapping
    public List<TaskRecord> getAllTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskRecord getTask(@PathVariable Long id) {
        return service.getTaskById(id);
    }
}