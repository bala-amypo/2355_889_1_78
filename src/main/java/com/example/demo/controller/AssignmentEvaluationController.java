package com.example.demo.controller;

import com.example.demo.dto.AssignmentEvaluationRequest;
import com.example.demo.model.AssignmentEvaluationRecord;
import com.example.demo.service.AssignmentEvaluationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Assignment Evaluations", description = "Assignment evaluation operations")
public class AssignmentEvaluationController {
    
    private final AssignmentEvaluationService service;
    
    public AssignmentEvaluationController(AssignmentEvaluationService service) {
        this.service = service;
    }
    
    @PostMapping
    @Operation(summary = "Submit evaluation for assignment")
    public ResponseEntity<AssignmentEvaluationRecord> submitEvaluation(
            @RequestBody AssignmentEvaluationRequest request) {
        return ResponseEntity.ok(service.evaluateAssignment(request));
    }
    
    @GetMapping("/assignment/{assignmentId}")
    @Operation(summary = "Get evaluations by assignment")
    public ResponseEntity<List<AssignmentEvaluationRecord>> getByAssignment(@PathVariable Long assignmentId) {
        return ResponseEntity.ok(service.getEvaluationsByAssignment(assignmentId));
    }
    
    @GetMapping
    @Operation(summary = "Get all evaluations")
    public ResponseEntity<List<AssignmentEvaluationRecord>> getAllEvaluations() {
        return ResponseEntity.ok(service.getAllEvaluations());
    }
}
