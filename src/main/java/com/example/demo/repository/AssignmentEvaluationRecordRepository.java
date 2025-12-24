package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface AssignmentEvaluationRecordRepository
        extends JpaRepository<AssignmentEvaluationRecord, Long> {

    List<AssignmentEvaluationRecord> findByAssignmentId(Long assignmentId);
}
