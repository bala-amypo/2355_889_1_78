package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Task Record Controller")
public class TaskRecordController {

    @PostMapping
    public String createTask(@RequestBody Object task) {
        return "Task created";
    }

    @PutMapping("/{id}")
    public String updateTask(@PathVariable Long id) {
        return "Task updated " + id;
    }

    @GetMapping("/open")
    public String listOpenTasks() {
        return "List open tasks";
    }

    @GetMapping("/{id}")
    public String getTask(@PathVariable Long id) {
        return "Get task " + id;
    }

    @GetMapping
    public String listTasks() {
        return "List all tasks";
    }
}
