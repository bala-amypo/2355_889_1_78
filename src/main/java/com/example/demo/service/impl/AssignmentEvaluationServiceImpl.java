package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.example.demo.model.AssignmentEvaluationRecord;
import com.example.demo.repository.AssignmentEvaluationRecordRepository;
import com.example.demo.service.AssignmentEvaluationService;

@Service
public class AssignmentEvaluationServiceImpl implements AssignmentEvaluationService {

    @Autowired
    AssignmentEvaluationRecordRepository repo;

    public AssignmentEvaluationRecord evaluateAssignment(AssignmentEvaluationRecord evaluation) {
        return repo.save(evaluation);
    }

    public List<AssignmentEvaluationRecord> getEvaluationsByAssignment(Long assignmentId) {
        return repo.findByAssignmentId(assignmentId);
    }

    public List<AssignmentEvaluationRecord> getAllEvaluations() {
        return repo.findAll();
    }
}