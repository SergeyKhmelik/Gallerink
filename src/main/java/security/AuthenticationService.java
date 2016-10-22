package security;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {

    TokenInfo authenticate(String login, String password);

    boolean checkToken(String token);

    void logout(String token);

    UserDetails currentUser();

}