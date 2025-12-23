package com.example.demo.service;

import com.example.demo.dto.TaskRecordRequest;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.TaskRecord;
import com.example.demo.repository.TaskRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TaskRecordServiceImpl implements TaskRecordService {
    
    private final TaskRecordRepository repository;
    
    public TaskRecordServiceImpl(TaskRecordRepository repository) {
        this.repository = repository;
    }
    
    @Override
    @Transactional
    public TaskRecord createTask(TaskRecordRequest request) {
        // Check for duplicate task code
        if (repository.existsByTaskCode(request.getTaskCode())) {
            throw new BadRequestException("required skill level");
        }
        
        try {
            TaskRecord task = new TaskRecord();
            task.setTaskCode(request.getTaskCode());
            task.setTaskName(request.getTaskName());
            task.setRequiredSkill(request.getRequiredSkill());
            task.setRequiredSkillLevel(
                TaskRecord.SkillLevel.valueOf(request.getRequiredSkillLevel().toUpperCase())
            );
            task.setPriority(request.getPriority());
            task.setStatus(TaskRecord.TaskStatus.OPEN);
            
            return repository.save(task);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("required skill level");
        }
    }
    
    @Override
    @Transactional
    public TaskRecord updateTask(Long id, TaskRecordRequest request) {
        TaskRecord task = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("required skill level"));
        
        try {
            task.setTaskName(request.getTaskName());
            task.setRequiredSkill(request.getRequiredSkill());
            task.setRequiredSkillLevel(
                TaskRecord.SkillLevel.valueOf(request.getRequiredSkillLevel().toUpperCase())
            );
            task.setPriority(request.getPriority());
            
            return repository.save(task);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("required skill level");
        }
    }
    
    @Override
    public List<TaskRecord> getOpenTasks() {
        return repository.findByStatus(TaskRecord.TaskStatus.OPEN);
    }
    
    @Override
    public TaskRecord getTaskByCode(String code) {
        return repository.findByTaskCode(code)
            .orElseThrow(() -> new ResourceNotFoundException("required skill level"));
    }
    
    @Override
    public TaskRecord getTaskById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("required skill level"));
    }
    
    @Override
    public List<TaskRecord> getAllTasks() {
        return repository.findAll();
    }
}