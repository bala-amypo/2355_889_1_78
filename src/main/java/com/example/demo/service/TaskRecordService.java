package com.example.demo.service;

import java.util.List;
import com.example.demo.model.TaskRecord;

public interface TaskRecordService {

    TaskRecord createTask(TaskRecord task);

    TaskRecord getTaskById(Long id);

    List<TaskRecord> getAllTasks();
}