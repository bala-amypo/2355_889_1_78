package com.example.demo.dto;

public class TaskRecordRequest {

    private String taskCode;
    private String taskName;
    private String requiredSkill;
    private String requiredSkillLevel;
    private String priority;

    public TaskRecordRequest() {}

    public String getTaskCode() { return taskCode; }
    public void setTaskCode(String taskCode) { this.taskCode = taskCode; }

    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }

    public String getRequiredSkill() { return requiredSkill; }
    public void setRequiredSkill(String requiredSkill) {
        this.requiredSkill = requiredSkill;
    }

    public String getRequiredSkillLevel() { return requiredSkillLevel; }
    public void setRequiredSkillLevel(String requiredSkillLevel) {
        this.requiredSkillLevel = requiredSkillLevel;
    }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
}
