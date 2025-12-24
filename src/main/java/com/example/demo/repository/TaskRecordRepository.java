package com.example.demo.repository;

import com.example.demo.model.TaskRecord;
import java.util.List;
import java.util.Optional;

public interface TaskRecordRepository {

    TaskRecord save(TaskRecord task);

    Optional<TaskRecord> findById(Long id);

    Optional<TaskRecord> findByTaskCode(String taskCode);

    List<TaskRecord> findAll();

    List<TaskRecord> findByStatus(String status);
}
