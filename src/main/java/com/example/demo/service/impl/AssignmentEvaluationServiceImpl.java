package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.AssignmentEvaluationRecord;
import com.example.demo.repository.AssignmentEvaluationRecordRepository;
import com.example.demo.repository.TaskAssignmentRecordRepository;
import com.example.demo.service.AssignmentEvaluationService;

import java.util.List;

public class AssignmentEvaluationServiceImpl implements AssignmentEvaluationService {

    private final AssignmentEvaluationRecordRepository evalRepo;
    private final TaskAssignmentRecordRepository assignmentRepo;

    public AssignmentEvaluationServiceImpl(
            AssignmentEvaluationRecordRepository evalRepo,
            TaskAssignmentRecordRepository assignmentRepo) {
        this.evalRepo = evalRepo;
        this.assignmentRepo = assignmentRepo;
    }

    @Override
    public AssignmentEvaluationRecord evaluateAssignment(
            AssignmentEvaluationRecord record) {

        assignmentRepo.findById(record.getAssignmentId())
                .orElseThrow(() -> new BadRequestException("Assignment not found"));

        return evalRepo.save(record);
    }

    @Override
    public List<AssignmentEvaluationRecord> getEvaluationsByAssignment(Long assignmentId) {
        return evalRepo.findByAssignmentId(assignmentId);
    }
}
