package com.example.demo.service;

import java.util.List;
import com.example.demo.model.AssignmentEvaluationRecord;

public interface AssignmentEvaluationService {

    AssignmentEvaluationRecord addEvaluation(AssignmentEvaluationRecord evaluation);

    AssignmentEvaluationRecord getEvaluationById(Long id);

    List<AssignmentEvaluationRecord> getEvaluationsByAssignment(Long assignmentId);

    List<AssignmentEvaluationRecord> getAllEvaluations();
}