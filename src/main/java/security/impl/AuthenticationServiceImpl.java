package security.impl;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import security.AuthenticationService;
import security.TokenInfo;
import security.TokenManager;

public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, TokenManager tokenManager) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
    }

    @Override
    public TokenInfo authenticate(String login, String password) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(login, password);

        authentication = authenticationManager.authenticate(authentication);
        // Here principal=UserDetails (UserContext in our case), credentials=null (security reasons)
        SecurityContextHolder.getContext().setAuthentication(authentication);

        if (authentication.getPrincipal() != null) {
            UserDetails userContext = (UserDetails) authentication.getPrincipal();
            TokenInfo newToken = tokenManager.createNewToken(userContext);
            if (newToken == null) {
                return null;
            }
            return newToken;
        }
        return null;
    }

    @Override
    public boolean checkToken(String token) {
        UserDetails userDetails = tokenManager.getUserDetails(token);
        if (userDetails == null) {
            return false;
        }

        Authentication securityToken = new PreAuthenticatedAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(securityToken);

        return true;
    }

    @Override
    public void logout(String token) {
        tokenManager.removeToken(token);
        SecurityContextHolder.clearContext();
    }

    @Override
    public UserDetails currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        return (UserDetails) authentication.getPrincipal();
    }
}
