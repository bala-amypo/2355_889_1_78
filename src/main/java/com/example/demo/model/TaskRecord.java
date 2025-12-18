package com.example.demo.model;


import java.time.*;
import jakarta.persistence.*;

@Entity
@Table(name = "task_record")
public class TaskRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String taskCode;

    private String taskName;
    private String requiredSkill;
    private String requiredSkillLevel;
    private String priority;
    private String status;

    private LocalDateTime createdAt;

    @PrePersist
    public void setDefaults() {
        this.createdAt = LocalDateTime.now();
        this.status = "OPEN";
    }

    public TaskRecord() {}

    public TaskRecord(String taskCode, String taskName,
                      String requiredSkill, String requiredSkillLevel,
                      String priority) {
        this.taskCode = taskCode;
        this.taskName = taskName;
        this.requiredSkill = requiredSkill;
        this.requiredSkillLevel = requiredSkillLevel;
        this.priority = priority;
    }

    // getters & setters
    public Long getId() { return id; }
    public String getTaskCode() { return taskCode; }
    public void setTaskCode(String taskCode) { this.taskCode = taskCode; }
    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }
    public String getRequiredSkill() { return requiredSkill; }
    public void setRequiredSkill(String requiredSkill) { this.requiredSkill = requiredSkill; }
    public String getRequiredSkillLevel() { return requiredSkillLevel; }
    public void setRequiredSkillLevel(String requiredSkillLevel) { this.requiredSkillLevel = requiredSkillLevel; }
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    public String getStatus() { return status; }
    public String getTitle() {
    return title;
}

public void setStatus(String status) {
    this.status = status;
}

}
