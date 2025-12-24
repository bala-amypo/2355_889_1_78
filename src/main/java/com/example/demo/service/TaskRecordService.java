package com.example.demo.service;

import com.example.demo.model.TaskRecord;
import java.util.*;

public interface TaskRecordService {

    TaskRecord createTask(TaskRecord task);

    TaskRecord updateTask(Long id, TaskRecord task);

    List<TaskRecord> getOpenTasks();

    Optional<TaskRecord> getTaskByCode(String code);

    List<TaskRecord> getAllTasks();
}
