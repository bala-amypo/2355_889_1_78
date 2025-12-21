package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.example.demo.model.AssignmentEvaluationRecord;
import com.example.demo.service.AssignmentEvaluationService;

@RestController
@RequestMapping("/api/evaluations")
public class AssignmentEvaluationController {

    @Autowired
    AssignmentEvaluationService service;

    @PostMapping
    public AssignmentEvaluationRecord submit(@RequestBody AssignmentEvaluationRecord e) {
        return service.evaluateAssignment(e);
    }

    @GetMapping("/assignment/{assignmentId}")
    public List<AssignmentEvaluationRecord> byAssignment(@PathVariable Long assignmentId) {
        return service.getEvaluationsByAssignment(assignmentId);
    }

    @GetMapping
    public List<AssignmentEvaluationRecord> all() {
        return service.getAllEvaluations();
    }
}