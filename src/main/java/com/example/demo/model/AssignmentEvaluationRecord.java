package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

import java.time.LocalDateTime;

@Entity
public class AssignmentEvaluationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long assignmentId;
    private Integer rating;
    private String feedback;

    private LocalDateTime evaluatedAt;

    @PrePersist
    public void onEvaluate() {
        this.evaluatedAt = LocalDateTime.now();
    }

    // ---------------- GETTERS ----------------

    public Long getId() {
        return id;
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public Integer getRating() {
        return rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public LocalDateTime getEvaluatedAt() {
        return evaluatedAt;
    }

    // ---------------- SETTERS ----------------

    public void setId(Long id) {
        this.id = id;
    }

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setEvaluatedAt(LocalDateTime evaluatedAt) {
        this.evaluatedAt = evaluatedAt;
    }
}