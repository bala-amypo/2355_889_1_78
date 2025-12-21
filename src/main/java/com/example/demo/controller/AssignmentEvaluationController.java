package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
@RestController
@RequestMapping("/api/evaluations")
@Tag(name = "Assignment Evaluation Controller")
public class AssignmentEvaluationController {

    @PostMapping
    public String submitEvaluation(@RequestBody Object evaluation) {
        return "Evaluation submitted";
    }

    @GetMapping("/assignment/{assignmentId}")
    public String getByAssignment(@PathVariable Long assignmentId) {
        return "Evaluation for assignment " + assignmentId;
    }

    @GetMapping
    public String listEvaluations() {
        return "List all evaluations";
    }
}
