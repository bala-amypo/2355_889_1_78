package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface TaskAssignmentRecordRepository
        extends JpaRepository<TaskAssignmentRecord, Long> {

    boolean existsByTaskIdAndStatus(Long taskId, String status);
    List<TaskAssignmentRecord> findByTaskId(Long taskId);
    List<TaskAssignmentRecord> findByVolunteerId(Long volunteerId);
}
