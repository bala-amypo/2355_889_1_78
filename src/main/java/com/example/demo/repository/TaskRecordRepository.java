package com.example.demo.repository;

import com.example.demo.model.TaskRecord;
import com.example.demo.model.TaskRecord.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRecordRepository extends JpaRepository<TaskRecord, Long> {
    Optional<TaskRecord> findByTaskCode(String taskCode);
    List<TaskRecord> findByStatus(TaskStatus status);
    boolean existsByTaskCode(String taskCode);
}