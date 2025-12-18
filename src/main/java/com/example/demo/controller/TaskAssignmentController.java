package com.example.demo.controller;
import com.example.demo.service.TaskAssignmentService;
import com.example.demo.model.TaskAssignmentRecord;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assignments")
public class TaskAssignmentController {
    private final TaskAssignmentService service;
    public TaskAssignmentController(TaskAssignmentService s){this.service=s;}

    @PostMapping("/assign/{id}")
    public TaskAssignmentRecord assign(@PathVariable Long id){
        return service.assignTask(id);
    }
}
