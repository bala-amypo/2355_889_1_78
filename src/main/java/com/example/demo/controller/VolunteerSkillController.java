
@RestController
@RequestMapping("/api/skills")
@Tag(name = "Volunteer Skill Controller")
public class VolunteerSkillController {

    @PostMapping
    public String addOrUpdateSkill(@RequestBody Object skill) {
        return "Skill added/updated";
    }

    @GetMapping("/volunteer/{volunteerId}")
    public String getSkillsByVolunteer(@PathVariable Long volunteerId) {
        return "Skills for volunteer " + volunteerId;
    }

    @GetMapping("/{id}")
    public String getSkill(@PathVariable Long id) {
        return "Get skill " + id;
    }

    @GetMapping
    public String listSkills() {
        return "List all skills";
    }
}
