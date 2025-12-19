package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AssignmentEvaluationRecord;
import com.example.demo.repository.AssignmentEvaluationRecordRepository;
import com.example.demo.service.AssignmentEvaluationService;

@Service
public class AssignmentEvaluationServiceImpl
        implements AssignmentEvaluationService {

    @Autowired
    private AssignmentEvaluationRecordRepository repository;

    @Override
    public AssignmentEvaluationRecord addEvaluation(
            AssignmentEvaluationRecord evaluation) {
        return repository.save(evaluation);
    }

    @Override
    public AssignmentEvaluationRecord getEvaluationById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<AssignmentEvaluationRecord> getEvaluationsByAssignment(
            Long assignmentId) {
        return repository.findByAssignmentId(assignmentId);
    }

    @Override
    public List<AssignmentEvaluationRecord> getAllEvaluations() {
        return repository.findAll();
    }
}