
@RestController
@RequestMapping("/api/assignments")
@Tag(name = "Task Assignment Controller")
public class TaskAssignmentController {

    @PostMapping("/assign/{taskId}")
    public String autoAssign(@PathVariable Long taskId) {
        return "Auto assigned task " + taskId;
    }

    @PutMapping("/{id}/status")
    public String updateStatus(@PathVariable Long id) {
        return "Assignment status updated " + id;
    }

    @GetMapping("/volunteer/{volunteerId}")
    public String getByVolunteer(@PathVariable Long volunteerId) {
        return "Assignments for volunteer " + volunteerId;
    }

    @GetMapping("/task/{taskId}")
    public String getByTask(@PathVariable Long taskId) {
        return "Assignments for task " + taskId;
    }

    @GetMapping
    public String listAssignments() {
        return "List all assignments";
    }
}
