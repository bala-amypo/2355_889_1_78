package com.example.demo.repository;

import com.example.demo.model.TaskAssignmentRecord;
import java.util.List;
import java.util.Optional;

public interface TaskAssignmentRecordRepository {

    TaskAssignmentRecord save(TaskAssignmentRecord record);

    Optional<TaskAssignmentRecord> findById(Long id);

    boolean existsByTaskIdAndStatus(Long taskId, String status);

    List<TaskAssignmentRecord> findByTaskId(Long taskId);

    List<TaskAssignmentRecord> findByVolunteerId(Long volunteerId);

    List<TaskAssignmentRecord> findAll();
}
