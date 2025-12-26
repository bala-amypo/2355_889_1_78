@Component
public class JwtTokenProvider {

    private final String secretKey = "your_secret_key_here";

    // Updated method to accept multiple parameters
    public String generateToken(Authentication authentication, Long userId, String username) {
        // TODO: implement JWT creation logic using all 3 parameters
        return "dummy-token-for-" + username + "-" + userId;
    }

    // Keep other methods as is
    public boolean validateToken(String token) {
        return true;
    }

    public String getUsernameFromToken(String token) {
        return "username-from-token";
    }
}
