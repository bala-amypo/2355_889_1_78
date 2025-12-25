package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.AssignmentEvaluationRecord;
import com.example.demo.model.TaskAssignmentRecord;
import com.example.demo.repository.AssignmentEvaluationRecordRepository;
import com.example.demo.repository.TaskAssignmentRecordRepository;
import com.example.demo.service.AssignmentEvaluationService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssignmentEvaluationServiceImpl implements AssignmentEvaluationService {
    
    private final AssignmentEvaluationRecordRepository evaluationRepository;
    private final TaskAssignmentRecordRepository assignmentRepository;
    
    public AssignmentEvaluationServiceImpl(
            AssignmentEvaluationRecordRepository evaluationRepository,
            TaskAssignmentRecordRepository assignmentRepository) {
        this.evaluationRepository = evaluationRepository;
        this.assignmentRepository = assignmentRepository;
    }
    
    @Override
    public AssignmentEvaluationRecord evaluateAssignment(AssignmentEvaluationRecord evaluation) {
        TaskAssignmentRecord assignment = assignmentRepository
            .findById(evaluation.getAssignmentId())
            .orElseThrow(() -> new BadRequestException("Assignment not found"));
        
        if (!"COMPLETED".equals(assignment.getStatus())) {
            throw new BadRequestException("Can only evaluate COMPLETED assignments");
        }
        
        return evaluationRepository.save(evaluation);
    }
    
    @Override
    public List<AssignmentEvaluationRecord> getEvaluationsByAssignment(Long assignmentId) {
        return evaluationRepository.findByAssignmentId(assignmentId);
    }
}