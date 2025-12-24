package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.TaskRecord;
import com.example.demo.repository.TaskRecordRepository;
import com.example.demo.service.TaskRecordService;

import java.util.*;

public class TaskRecordServiceImpl implements TaskRecordService {

    private final TaskRecordRepository repo;

    public TaskRecordServiceImpl(TaskRecordRepository repo) {
        this.repo = repo;
    }

    public TaskRecord createTask(TaskRecord task) {
        if (task.getStatus() == null)
            task.setStatus("OPEN");
        return repo.save(task);
    }

    public TaskRecord updateTask(Long id, TaskRecord updated) {
        TaskRecord existing = repo.findById(id)
                .orElseThrow(() -> new BadRequestException("Task not found"));

        existing.setTaskName(updated.getTaskName());
        existing.setRequiredSkill(updated.getRequiredSkill());
        existing.setRequiredSkillLevel(updated.getRequiredSkillLevel());
        existing.setPriority(updated.getPriority());
        existing.setStatus(updated.getStatus());

        return repo.save(existing);
    }

    public List<TaskRecord> getOpenTasks() {
        return repo.findByStatus("OPEN");
    }

    public Optional<TaskRecord> getTaskByCode(String code) {
        return repo.findByTaskCode(code);
    }

    public List<TaskRecord> getAllTasks() {
        return repo.findAll();
    }
}
