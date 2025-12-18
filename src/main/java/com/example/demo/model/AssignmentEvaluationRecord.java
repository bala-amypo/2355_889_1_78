package com.example.demo.model;

import java.time.*;
import jakarta.persistence.*;

@Entity
@Table(name = "assignment_evaluation_record")
public class AssignmentEvaluationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long assignmentId;

    private Integer rating; // 1–5

    private String feedback;

    private LocalDateTime evaluatedAt;

    @PrePersist
    public void setEvaluatedAt() {
        this.evaluatedAt = LocalDateTime.now();
    }

    public AssignmentEvaluationRecord() {}

    public AssignmentEvaluationRecord(Long assignmentId, Integer rating, String feedback) {
        this.assignmentId = assignmentId;
        this.rating = rating;
        this.feedback = feedback;
    }

    // getters & setters
    public Long getId() { return id; }
    public Long getAssignmentId() { return assignmentId; }
    public void setAssignmentId(Long assignmentId) { this.assignmentId = assignmentId; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
}
