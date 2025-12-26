// src/main/java/com/example/demo/controller/AssignmentEvaluationController.java
package com.example.demo.controller;

import com.example.demo.model.AssignmentEvaluationRecord;
import com.example.demo.service.AssignmentEvaluationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
@Tag(name = "AssignmentEvaluationController")
public class AssignmentEvaluationController {

    @Autowired
    private AssignmentEvaluationService service;

    @PostMapping("/")
    @Operation(summary = "Submit evaluation")
    public AssignmentEvaluationRecord submit(@RequestBody AssignmentEvaluationRecord evaluation) {
        return service.evaluateAssignment(evaluation);
    }

    @GetMapping("/assignment/{assignmentId}")
    @Operation(summary = "Get by assignment")
    public List<AssignmentEvaluationRecord> byAssignment(@PathVariable Long assignmentId) {
        return service.getEvaluationsByAssignment(assignmentId);
    }

    @GetMapping("/")
    @Operation(summary = "List all")
    public List<AssignmentEvaluationRecord> list() {
        return service.getAllEvaluations();
    }
}