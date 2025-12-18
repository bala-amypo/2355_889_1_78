package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.TaskRecordRepository;
public interface TaskRecordRepository
        extends JpaRepository<TaskRecord, Long> {

    List<TaskRecord> findByStatus(String status);
    TaskRecord findByCode(String code);
}
