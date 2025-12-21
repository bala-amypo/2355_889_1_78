package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.example.demo.model.TaskRecord;
import com.example.demo.repository.TaskRecordRepository;
import com.example.demo.service.TaskRecordService;

@Service
public class TaskRecordServiceImpl implements TaskRecordService {

    @Autowired
    private TaskRecordRepository repo;

    @Override
    public TaskRecord createTask(TaskRecord task) {
        return repo.save(task);
    }

    @Override
    public TaskRecord getTaskById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public TaskRecord getTaskByCode(String taskCode) {
        return repo.findByTaskCode(taskCode);
    }

    @Override
    public List<TaskRecord> getAllTasks() {
        return repo.findAll();
    }

    @Override
    public TaskRecord updateStatus(Long id, String status) {
        TaskRecord task = repo.findById(id).orElse(null);
        if (task != null) {
            task.setStatus(status);
            return repo.save(task);
        }
        return null;
    }
}