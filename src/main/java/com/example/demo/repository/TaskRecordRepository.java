package com.example.demo.repository;

import com.example.demo.model.TaskRecord;
import java.util.*;

public interface TaskRecordRepository {

    Optional<TaskRecord> findById(Long id);

    Optional<TaskRecord> findByTaskCode(String taskCode);

    List<TaskRecord> findByStatus(String status);

    List<TaskRecord> findAll();

    TaskRecord save(TaskRecord task);
}
