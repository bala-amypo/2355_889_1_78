package com.example.demo.service;

import com.example.demo.dto.AssignmentEvaluationRequest;
import com.example.demo.model.AssignmentEvaluationRecord;
import java.util.List;

public interface AssignmentEvaluationService {
    AssignmentEvaluationRecord evaluateAssignment(AssignmentEvaluationRequest request);
    List<AssignmentEvaluationRecord> getEvaluationsByAssignment(Long assignmentId);
    List<AssignmentEvaluationRecord> getAllEvaluations();
    AssignmentEvaluationRecord getEvaluationById(Long id);
}