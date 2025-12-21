package com.example.demo.service;

import java.util.List;
import com.example.demo.model.TaskAssignmentRecord;

public interface TaskAssignmentService {

    TaskAssignmentRecord assignTask(TaskAssignmentRecord assignment);

    TaskAssignmentRecord getAssignmentById(Long id);

    List<TaskAssignmentRecord> getAssignmentsByVolunteer(Long volunteerId);

    List<TaskAssignmentRecord> getAssignmentsByTask(Long taskId);
}