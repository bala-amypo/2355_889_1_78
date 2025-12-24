package com.example.demo.repository;

import com.example.demo.model.AssignmentEvaluationRecord;
import java.util.List;

public interface AssignmentEvaluationRecordRepository {

    AssignmentEvaluationRecord save(AssignmentEvaluationRecord record);

    List<AssignmentEvaluationRecord> findByAssignmentId(Long assignmentId);
}
