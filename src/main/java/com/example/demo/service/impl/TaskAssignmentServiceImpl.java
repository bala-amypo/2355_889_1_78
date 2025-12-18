// TaskAssignmentServiceImpl.java
package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.TaskAssignmentService;
import com.example.demo.util.SkillLevelUtil;
import com.example.demo.exception.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class TaskAssignmentServiceImpl
        implements TaskAssignmentService {

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
        TaskRecord task = tr.findById(taskId)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        if (ar.existsByTaskIdAndStatus(taskId,"ACTIVE"))
            throw new BadRequestException("ACTIVE assignment");

        for (VolunteerProfile v : vr.findByAvailabilityStatus("AVAILABLE")) {
            for (VolunteerSkillRecord s : sr.findByVolunteerId(v.getId())) {
                if (s.getSkillName().equals(task.getRequiredSkill()) &&
                    SkillLevelUtil.levelRank(s.getSkillLevel()) >=
                    SkillLevelUtil.levelRank(task.getRequiredSkillLevel())) {

                    TaskAssignmentRecord r = new TaskAssignmentRecord();
                    r.setTaskId(task.getId());
                    r.setVolunteerId(v.getId());
                    r.setAssignedAt(LocalDateTime.now());
                    return ar.save(r);
                }
            }
        }
        throw new BadRequestException("required skill level");
    }
}
