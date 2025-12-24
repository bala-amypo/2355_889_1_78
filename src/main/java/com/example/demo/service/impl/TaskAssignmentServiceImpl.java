package com.example.demo.service.impl;

import com.example.demo.service.TaskAssignmentService;
import com.example.demo.repository.*;
import com.example.demo.model.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.util.SkillLevelUtil;
import java.util.List;

public class TaskAssignmentServiceImpl implements TaskAssignmentService {

    private final TaskAssignmentRecordRepository ar;
    private final TaskRecordRepository tr;
    private final VolunteerProfileRepository vr;
    private final VolunteerSkillRecordRepository sr;

    public TaskAssignmentServiceImpl(
            TaskAssignmentRecordRepository ar,
            TaskRecordRepository tr,
            VolunteerProfileRepository vr,
            VolunteerSkillRecordRepository sr) {
        this.ar = ar;
        this.tr = tr;
        this.vr = vr;
        this.sr = sr;
    }

    public TaskAssignmentRecord assignTask(Long taskId) {
        TaskRecord task = tr.findById(taskId).orElseThrow();

        if (ar.existsByTaskIdAndStatus(taskId, "ACTIVE"))
            throw new BadRequestException("ACTIVE assignment exists");

        List<VolunteerProfile> volunteers =
                vr.findByAvailabilityStatus("AVAILABLE");

        if (volunteers.isEmpty())
            throw new BadRequestException("No AVAILABLE volunteers");

        VolunteerProfile v = volunteers.get(0);

        TaskAssignmentRecord rec = new TaskAssignmentRecord();
        rec.setTaskId(taskId);
        rec.setVolunteerId(v.getId());

        task.setStatus("ASSIGNED");
        tr.save(task);

        return ar.save(rec);
    }

    public List<TaskAssignmentRecord> getAllAssignments() {
        return ar.findAll();
    }

    public List<TaskAssignmentRecord> getAssignmentsByTask(Long taskId) {
        return ar.findByTaskId(taskId);
    }

    public List<TaskAssignmentRecord> getAssignmentsByVolunteer(Long vid) {
        return ar.findByVolunteerId(vid);
    }
}
