package com.example.demo.repository;

import com.example.demo.model.TaskAssignmentRecord;
import com.example.demo.model.TaskAssignmentRecord.AssignmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface TaskAssignmentRecordRepository extends JpaRepository<TaskAssignmentRecord, Long> {
    boolean existsByTaskIdAndStatus(Long taskId, String status);
    List<TaskAssignmentRecord> findByTaskId(Long taskId);
    List<TaskAssignmentRecord> findByVolunteerId(Long volunteerId);
}
