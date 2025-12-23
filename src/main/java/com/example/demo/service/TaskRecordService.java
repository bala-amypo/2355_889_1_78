package com.example.demo.service;

import com.example.demo.dto.TaskRecordRequest;
import com.example.demo.model.TaskRecord;
import java.util.List;

public interface TaskRecordService {
    TaskRecord createTask(TaskRecordRequest request);
    TaskRecord updateTask(Long id, TaskRecordRequest request);
    List<TaskRecord> getOpenTasks();
    TaskRecord getTaskByCode(String code);
    TaskRecord getTaskById(Long id);
    List<TaskRecord> getAllTasks();
}