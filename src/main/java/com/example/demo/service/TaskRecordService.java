package com.example.demo.service;

import com.example.demo.model.TaskRecord;

import java.util.List;
import java.util.Optional;

public interface TaskRecordService {
    TaskRecord createTask(TaskRecord task);
    TaskRecord updateTask(Long id, TaskRecord task);
    List<TaskRecord> getOpenTasks();
    TaskRecord getTaskById(Long id);           // <-- THIS WAS MISSING
    Optional<TaskRecord> getTaskByCode(String code);
    List<TaskRecord> getAllTasks();
}