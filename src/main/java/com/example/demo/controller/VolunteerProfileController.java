
@RestController
@RequestMapping("/api/volunteers")
@Tag(name = "Volunteer Profile Controller")
public class VolunteerProfileController {

    @PostMapping
    public String createVolunteer(@RequestBody Object volunteer) {
        return "Volunteer created";
    }

    @GetMapping("/{id}")
    public String getVolunteer(@PathVariable Long id) {
        return "Get volunteer " + id;
    }

    @GetMapping
    public String listVolunteers() {
        return "List all volunteers";
    }

    @PutMapping("/{id}/availability")
    public String updateAvailability(@PathVariable Long id) {
        return "Availability updated for " + id;
    }

    @GetMapping("/lookup/{volunteerId}")
    public String lookupVolunteer(@PathVariable Long volunteerId) {
        return "Lookup volunteer " + volunteerId;
    }
}
