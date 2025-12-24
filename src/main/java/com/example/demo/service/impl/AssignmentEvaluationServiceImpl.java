package com.example.demo.service.impl;

import com.example.demo.service.AssignmentEvaluationService;
import com.example.demo.repository.*;
import com.example.demo.model.*;
import java.util.List;

public class AssignmentEvaluationServiceImpl
        implements AssignmentEvaluationService {

    private final AssignmentEvaluationRecordRepository repo;
    private final TaskAssignmentRecordRepository ar;

    public AssignmentEvaluationServiceImpl(
            AssignmentEvaluationRecordRepository repo,
            TaskAssignmentRecordRepository ar) {
        this.repo = repo;
        this.ar = ar;
    }

    public AssignmentEvaluationRecord evaluateAssignment(AssignmentEvaluationRecord e) {
        return repo.save(e);
    }

    public List<AssignmentEvaluationRecord> getEvaluationsByAssignment(Long id) {
        return repo.findByAssignmentId(id);
    }
}
