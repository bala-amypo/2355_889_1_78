package com.example.demo.model;

import java.time.LocalDateTime;

public class AssignmentEvaluationRecord {
    private Long assignmentId;
    private Integer rating;
    private String feedback;
    private LocalDateTime evaluatedAt = LocalDateTime.now();

    public Long getAssignmentId() { return assignmentId; }
    public void setAssignmentId(Long assignmentId) { this.assignmentId = assignmentId; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    public LocalDateTime getEvaluatedAt() { return evaluatedAt; }
}
