package com.example.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.TaskRecordRepository;
Optional<TaskRecord> findByTaskCode(String code);

public interface TaskRecordRepository
        extends JpaRepository<TaskRecord, Long> {

    List<TaskRecord> findByStatus(String status);
    TaskRecord findByCode(String code);
}
