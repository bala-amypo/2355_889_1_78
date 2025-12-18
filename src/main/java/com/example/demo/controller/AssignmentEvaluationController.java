package com.example.demo.controller;

import com.example.demo.model.AssignmentEvaluationRecord;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
public class AssignmentEvaluationController {

    @PostMapping
    @Operation(summary = "Submit evaluation")
    public AssignmentEvaluation submitEvaluation(@RequestBody AssignmentEvaluation evaluation) {
        return evaluation;
    }

    @GetMapping("/assignment/{assignmentId}")
    @Operation(summary = "Get evaluation by assignment")
    public AssignmentEvaluation getByAssignment(@PathVariable Long assignmentId) {
        return new AssignmentEvaluation();
    }

    @GetMapping
    @Operation(summary = "List all evaluations")
    public List<AssignmentEvaluation> listAllEvaluations() {
        return List.of();
    }
}
