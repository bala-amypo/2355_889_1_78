package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.AssignmentEvaluationRecord;
import com.example.demo.service.AssignmentEvaluationService;

@RestController
@RequestMapping("/api/evaluations")
@CrossOrigin
public class AssignmentEvaluationController {

    @Autowired
    private AssignmentEvaluationService service;

    // Add evaluation
    @PostMapping
    public AssignmentEvaluationRecord addEvaluation(
            @RequestBody AssignmentEvaluationRecord evaluation) {
        return service.addEvaluation(evaluation);
    }

    // Get evaluation by ID
    @GetMapping("/{id}")
    public AssignmentEvaluationRecord getEvaluation(@PathVariable Long id) {
        return service.getEvaluationById(id);
    }

    // Get evaluations by assignment
    @GetMapping("/assignment/{assignmentId}")
    public List<AssignmentEvaluationRecord> getByAssignment(
            @PathVariable Long assignmentId) {
        return service.getEvaluationsByAssignment(assignmentId);
    }

    // Get all evaluations
    @GetMapping
    public List<AssignmentEvaluationRecord> getAll() {
        return service.getAllEvaluations();
    }
}