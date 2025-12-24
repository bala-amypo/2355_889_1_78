package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "task_assignment_record")
public class TaskAssignmentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long taskId;
    private Long volunteerId;

    private String status = "ACTIVE";

    // getters & setters
}
