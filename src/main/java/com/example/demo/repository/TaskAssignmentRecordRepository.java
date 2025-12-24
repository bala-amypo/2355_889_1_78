package com.example.demo.repository;

import com.example.demo.model.TaskAssignmentRecord;
import java.util.*;

public interface TaskAssignmentRecordRepository {

    boolean existsByTaskIdAndStatus(Long taskId, String status);

    Optional<TaskAssignmentRecord> findById(Long id);

    List<TaskAssignmentRecord> findByTaskId(Long taskId);

    List<TaskAssignmentRecord> findByVolunteerId(Long volunteerId);

    List<TaskAssignmentRecord> findAll();

    TaskAssignmentRecord save(TaskAssignmentRecord record);
}
