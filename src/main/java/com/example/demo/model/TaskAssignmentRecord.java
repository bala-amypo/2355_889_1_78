package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TaskAssignmentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long taskId;
    private Long volunteerId;
    private String status;
    private String notes;

    private LocalDateTime assignedAt;

    @PrePersist
    public void onAssign() {
        this.assignedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = "ASSIGNED";
        }
    }

    // Getters
    public Long getId() { return id; }
    public Long getTaskId() { return taskId; }
    public Long getVolunteerId() { return volunteerId; }
    public String getStatus() { return status; }
    public String getNotes() { return notes; }
    public LocalDateTime getAssignedAt() { return assignedAt; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setTaskId(Long taskId) { this.taskId = taskId; }
    public void setVolunteerId(Long volunteerId) { this.volunteerId = volunteerId; }
    public void setStatus(String status) { this.status = status; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setAssignedAt(LocalDateTime assignedAt) { this.assignedAt = assignedAt; }
}