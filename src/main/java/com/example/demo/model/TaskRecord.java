package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task_records")
public class TaskRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String taskCode;
    
    @Column(nullable = false)
    private String taskName;
    
    @Column(nullable = false)
    private String requiredSkill;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SkillLevel requiredSkillLevel;
    
    @Column(nullable = false)
    private String priority;
    
    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.OPEN;
    
    private LocalDateTime createdAt;
    
    public enum SkillLevel {
        BEGINNER, INTERMEDIATE, EXPERT
    }
    
    public enum TaskStatus {
        OPEN, ASSIGNED, COMPLETED, CANCELLED
    }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) status = TaskStatus.OPEN;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTaskCode() { return taskCode; }
    public void setTaskCode(String taskCode) { this.taskCode = taskCode; }
    
    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }
    
    public String getRequiredSkill() { return requiredSkill; }
    public void setRequiredSkill(String requiredSkill) { this.requiredSkill = requiredSkill; }
    
    public SkillLevel getRequiredSkillLevel() { return requiredSkillLevel; }
    public void setRequiredSkillLevel(SkillLevel level) { this.requiredSkillLevel = level; }
    
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    
    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}