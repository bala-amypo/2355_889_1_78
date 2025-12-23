package com.example.demo.service;

import com.example.demo.dto.AssignmentEvaluationRequest;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AssignmentEvaluationRecord;
import com.example.demo.repository.AssignmentEvaluationRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AssignmentEvaluationServiceImpl implements AssignmentEvaluationService {
    
    private final AssignmentEvaluationRecordRepository repository;
    
    public AssignmentEvaluationServiceImpl(AssignmentEvaluationRecordRepository repository) {
        this.repository = repository;
    }
    
    @Override
    @Transactional
    public AssignmentEvaluationRecord evaluateAssignment(AssignmentEvaluationRequest request) {
        // Validate rating (1-5 scale)
        if (request.getRating() < 1 || request.getRating() > 5) {
            throw new BadRequestException("Rating must be between 1 and 5");
        }
        
        AssignmentEvaluationRecord evaluation = new AssignmentEvaluationRecord();
        evaluation.setAssignmentId(request.getAssignmentId());
        evaluation.setRating(request.getRating());
        evaluation.setFeedback(request.getFeedback());
        
        return repository.save(evaluation);
    }
    
    @Override
    public List<AssignmentEvaluationRecord> getEvaluationsByAssignment(Long assignmentId) {
        return repository.findByAssignmentId(assignmentId);
    }
    
    @Override
    public List<AssignmentEvaluationRecord> getAllEvaluations() {
        return repository.findAll();
    }
    
    @Override
    public AssignmentEvaluationRecord getEvaluationById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Evaluation not found with ID: " + id));
    }
}
