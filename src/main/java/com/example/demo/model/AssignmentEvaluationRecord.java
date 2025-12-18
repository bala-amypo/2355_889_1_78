package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class AssignmentEvaluationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long assignmentId;
    private int rating;
    private String feedback;
    private LocalDateTime evaluatedAt;

    public AssignmentEvaluationRecord() {}

    public AssignmentEvaluationRecord(Long id, Long assignmentId,
            int rating, String feedback, LocalDateTime evaluatedAt) {
        this.id = id;
        this.assignmentId = assignmentId;
        this.rating = rating;
        this.feedback = feedback;
        this.evaluatedAt = evaluatedAt;
    }

    @PrePersist
    void pre() { this.evaluatedAt = LocalDateTime.now(); }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAssignmentId() { return assignmentId; }
    public void setAssignmentId(Long assignmentId) { this.assignmentId = assignmentId; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    public LocalDateTime getEvaluatedAt() { return evaluatedAt; }
    public void setEvaluatedAt(LocalDateTime evaluatedAt) {
        this.evaluatedAt = evaluatedAt;
    }
}
