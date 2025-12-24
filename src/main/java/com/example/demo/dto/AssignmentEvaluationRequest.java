package com.example.demo.dto;

public class AssignmentEvaluationRequest {

    private Long assignmentId;
    private Integer rating;
    private String feedback;

    public AssignmentEvaluationRequest() {}

    public Long getAssignmentId() { return assignmentId; }
    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
}
