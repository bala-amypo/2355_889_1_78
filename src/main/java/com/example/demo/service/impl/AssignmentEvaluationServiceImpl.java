package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.AssignmentEvaluationRecord;
import com.example.demo.model.TaskAssignmentRecord;
import com.example.demo.repository.AssignmentEvaluationRecordRepository;
import com.example.demo.repository.TaskAssignmentRecordRepository;
import com.example.demo.service.AssignmentEvaluationService;

import java.util.List;

public class AssignmentEvaluationServiceImpl implements AssignmentEvaluationService {

    private final AssignmentEvaluationRecordRepository evalRepo;
    private final TaskAssignmentRecordRepository assignmentRepo;

    public AssignmentEvaluationServiceImpl(
            AssignmentEvaluationRecordRepository e,
            TaskAssignmentRecordRepository a) {
        this.evalRepo = e;
        this.assignmentRepo = a;
    }

    public AssignmentEvaluationRecord evaluateAssignment(AssignmentEvaluationRecord eval) {

        TaskAssignmentRecord assignment =
                assignmentRepo.findById(eval.getAssignmentId())
                        .orElseThrow(() ->
                                new BadRequestException("Assignment not found"));

        if (!"COMPLETED".equals(assignment.getStatus()))
            throw new BadRequestException("Assignment not completed");

        return evalRepo.save(eval);
    }

    public List<AssignmentEvaluationRecord> getEvaluationsByAssignment(Long id) {
        return evalRepo.findByAssignmentId(id);
    }
}
