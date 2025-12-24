package com.example.demo.repository;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface TaskRecordRepository extends JpaRepository<TaskRecord, Long> {
    Optional<TaskRecord> findByTaskCode(String taskCode);
    List<TaskRecord> findByStatus(String status);
}
