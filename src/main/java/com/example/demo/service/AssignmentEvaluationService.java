package com.example.demo.service;

import com.example.demo.model.*;
import java.util.List;

public interface AssignmentEvaluationService {
    AssignmentEvaluationRecord evaluateAssignment(AssignmentEvaluationRecord e);
    List<AssignmentEvaluationRecord> getEvaluationsByAssignment(Long id);
}
