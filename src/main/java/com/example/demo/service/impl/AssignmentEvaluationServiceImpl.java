package com.example.demo.service.impl;

import com.example.demo.model.AssignmentEvaluationRecord;
import com.example.demo.repository.AssignmentEvaluationRecordRepository;
import com.example.demo.service.AssignmentEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentEvaluationServiceImpl implements AssignmentEvaluationService {

    @Autowired
    private AssignmentEvaluationRecordRepository repo;

    @Override
    public AssignmentEvaluationRecord evaluateAssignment(AssignmentEvaluationRecord evaluation) {
        return repo.save(evaluation);
    }

    @Override
    public List<AssignmentEvaluationRecord> getEvaluationsByAssignment(Long assignmentId) {
        return repo.findByAssignmentId(assignmentId);
    }

    @Override
    public List<AssignmentEvaluationRecord> getAllEvaluations() {
        return repo.findAll();
    }
}
