package com.example.demo.service;

import com.example.demo.model.*;
import java.util.List;

public interface TaskRecordService {
    TaskRecord createTask(TaskRecord t);
    TaskRecord updateTask(Long id, TaskRecord t);
    List<TaskRecord> getOpenTasks();
    List<TaskRecord> getAllTasks();
}
