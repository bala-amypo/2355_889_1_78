package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.TaskRecord;
import com.example.demo.repository.TaskRecordRepository;
import com.example.demo.service.TaskRecordService;

@Service
public class TaskRecordServiceImpl implements TaskRecordService {

    @Autowired
    private TaskRecordRepository repository;

    public TaskRecord createTask(TaskRecord task) {
        return repository.save(task);
    }

    public TaskRecord getTaskById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<TaskRecord> getAllTasks() {
        return repository.findAll();
    }
}