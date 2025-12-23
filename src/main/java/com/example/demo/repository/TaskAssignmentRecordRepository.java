package com.example.demo.repository;

import com.example.demo.model.TaskAssignmentRecord;
import com.example.demo.model.TaskAssignmentRecord.AssignmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskAssignmentRecordRepository extends JpaRepository<TaskAssignmentRecord, Long> {
    List<TaskAssignmentRecord> findByVolunteerId(Long volunteerId);
    List<TaskAssignmentRecord> findByTaskId(Long taskId);
    List<TaskAssignmentRecord> findByStatus(AssignmentStatus status);
}