package com.example.demo.service;

import java.util.List;
import com.example.demo.model.TaskRecord;

public interface TaskRecordService {

    TaskRecord createTask(TaskRecord task);

    TaskRecord getTaskById(Long id);

    TaskRecord getTaskByCode(String taskCode);

    List<TaskRecord> getAllTasks();

    TaskRecord updateStatus(Long id, String status);
}