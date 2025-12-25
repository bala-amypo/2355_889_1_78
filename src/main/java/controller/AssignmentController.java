package com.example.demo.controller;

import com.example.demo.model.AssignmentEvaluationRecord;
import com.example.demo.model.TaskAssignmentRecord;
import com.example.demo.service.AssignmentEvaluationService;
import com.example.demo.service.TaskAssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    
    private final TaskAssignmentService assignmentService;
    private final AssignmentEvaluationService evaluationService;
    
    public AssignmentController(TaskAssignmentService assignmentService,
                               AssignmentEvaluationService evaluationService) {
        this.assignmentService = assignmentService;
        this.evaluationService = evaluationService;
    }
    
    @PostMapping("/assign/{taskId}")
    public ResponseEntity<TaskAssignmentRecord> assignTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(assignmentService.assignTask(taskId));
    }
    
    @GetMapping
    public ResponseEntity<List<TaskAssignmentRecord>> getAllAssignments() {
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }
    
    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<TaskAssignmentRecord>> getAssignmentsByTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByTask(taskId));
    }
    
    @GetMapping("/volunteer/{volunteerId}")
    public ResponseEntity<List<TaskAssignmentRecord>> getAssignmentsByVolunteer(
            @PathVariable Long volunteerId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByVolunteer(volunteerId));
    }
    
    @PostMapping("/evaluate")
    public ResponseEntity<AssignmentEvaluationRecord> evaluateAssignment(
            @RequestBody AssignmentEvaluationRecord evaluation) {
        return ResponseEntity.ok(evaluationService.evaluateAssignment(evaluation));
    }
    
    @GetMapping("/evaluations/{assignmentId}")
    public ResponseEntity<List<AssignmentEvaluationRecord>> getEvaluations(
            @PathVariable Long assignmentId) {
        return ResponseEntity.ok(evaluationService.getEvaluationsByAssignment(assignmentId));
    }
}