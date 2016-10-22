package security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public final class TokenInfo {

    private final long created = System.currentTimeMillis();
    private final String token;
    private final UserDetails userDetails;
    // TODO expiration etc

    public TokenInfo(String token, UserDetails userDetails) {
        this.token = token;
        this.userDetails = userDetails;
    }

    public String getToken() {
        return token;
    }

    public long getCreated() {
        return created;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    @Override
    public String toString() {
        return "TokenInfo{" +
                "token='" + token + '\'' +
                ", userDetails" + userDetails +
                ", created=" + new Date(created) +
                '}';
    }
}
