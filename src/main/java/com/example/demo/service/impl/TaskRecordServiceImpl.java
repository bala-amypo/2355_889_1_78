package com.example.demo.service.impl;

import com.example.demo.service.TaskRecordService;
import com.example.demo.repository.TaskRecordRepository;
import com.example.demo.model.TaskRecord;
import java.util.List;

public class TaskRecordServiceImpl implements TaskRecordService {

    private final TaskRecordRepository repo;

    public TaskRecordServiceImpl(TaskRecordRepository repo) {
        this.repo = repo;
    }

    public TaskRecord createTask(TaskRecord t) {
        t.setStatus("OPEN");
        return repo.save(t);
    }

    public TaskRecord updateTask(Long id, TaskRecord t) {
        TaskRecord e = repo.findById(id).orElseThrow();
        e.setTaskName(t.getTaskName());
        e.setRequiredSkillLevel(t.getRequiredSkillLevel());
        e.setPriority(t.getPriority());
        return repo.save(e);
    }

    public List<TaskRecord> getOpenTasks() {
        return repo.findByStatus("OPEN");
    }

    public List<TaskRecord> getAllTasks() {
        return repo.findAll();
    }
}
