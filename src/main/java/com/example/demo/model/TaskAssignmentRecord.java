package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task_assignment_records")
public class TaskAssignmentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long taskId;

    private Long volunteerId;

    private LocalDateTime assignedAt;

    private String status; // ACTIVE / COMPLETED / CANCELLED

    @PrePersist
    public void prePersist() {
        this.assignedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = "ACTIVE";
        }
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getTaskId() { return taskId; }
    public void setTaskId(Long taskId) { this.taskId = taskId; }

    public Long getVolunteerId() { return volunteerId; }
    public void setVolunteerId(Long volunteerId) { this.volunteerId = volunteerId; }

    public LocalDateTime getAssignedAt() { return assignedAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
