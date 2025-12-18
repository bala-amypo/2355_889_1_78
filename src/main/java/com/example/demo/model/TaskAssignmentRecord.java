package com.example.demo.model;

import java.time.*;
import jakarta.persistence.*;

@Entity
@Table(name = "task_assignment_record")
public class TaskAssignmentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long taskId;
    private Long volunteerId;

    private LocalDateTime assignedAt;

    // ACTIVE / COMPLETED / CANCELLED
    private String status;

    private String notes;

    @PrePersist
    public void setDefaults() {
        this.assignedAt = LocalDateTime.now();
        this.status = "ACTIVE";
    }

    public TaskAssignmentRecord() {}

    public TaskAssignmentRecord(Long taskId, Long volunteerId, String notes) {
        this.taskId = taskId;
        this.volunteerId = volunteerId;
        this.notes = notes;
    }

    // getters & setters
    public Long getId() { return id; }
    public Long getTaskId() { return taskId; }
    public void setTaskId(Long taskId) { this.taskId = taskId; }
    public Long getVolunteerId() { return volunteerId; }
    public void setVolunteerId(Long volunteerId) { this.volunteerId = volunteerId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
