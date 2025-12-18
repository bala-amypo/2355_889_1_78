package com.example.demo.repository;
import com.example.demo.model.TaskAssignmentRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskAssignmentRecordRepository
        extends JpaRepository<TaskAssignmentRecord,Long>{
    boolean existsByTaskIdAndStatus(Long taskId,String status);
}
