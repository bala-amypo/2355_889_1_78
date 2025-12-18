package com.example.demo.service.impl;

import com.example.demo.model.TaskRecord;
import com.example.demo.repository.TaskRecordRepository;
import com.example.demo.service.TaskRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskRecordServiceImpl implements TaskRecordService {

    @Autowired
    private TaskRecordRepository repository;

    @Override
    public TaskRecord createTask(TaskRecord task) {
        return repository.save(task);
    }

    @Override
    public TaskRecord updateTask(Long id, TaskRecord updatedTask) {
        TaskRecord task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());
        task.setCode(updatedTask.getCode());
        return repository.save(task);
    }

    @Override
    public List<TaskRecord> getOpenTasks() {
        return repository.findByStatus("OPEN");
    }

    @Override
    public TaskRecord getTaskByCode(String code) {
        return repository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public List<TaskRecord> getAllTasks() {
        return repository.findAll();
    }
}
