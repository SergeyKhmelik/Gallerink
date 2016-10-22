package controller.authorization;


import controller.BaseController;
import domain.user.User;
import domain.user.UserRole;
import dto.auth.SignInDto;
import dto.user._User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.*;
import rest.ResponseWrapper;
import security.AuthenticationService;
import security.TokenInfo;
import service.user.UserService;

import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/api/authorization_", description = "Provides methods for authorization flow")
@RequestMapping("/api/authorization")
public class AuthorizationController extends BaseController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @ApiOperation(
            value = "Authorize user",
            notes = "Authorize user, if credentials are correct. Return Signed In user",
            response = _User.class,
            httpMethod = "POST"
    )
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public ResponseWrapper signIn(@RequestBody SignInDto signInDto, HttpServletResponse response) {
        TokenInfo token = authenticationService.authenticate(signInDto.getEmail(), signInDto.getPassword());
        if(token == null) {
            throw new AuthorizationServiceException("donzo");
        }
        response.setHeader("x-auth-token", token.getToken());
        return ok(token.getUserDetails());
    }

    @ApiOperation(
            value = "Sign up user",
            notes = "Signs user up by dto",
            response = _User.class,
            httpMethod = "POST"
    )
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseWrapper signUp(@RequestBody _User insertDto) {
        User user = new User();
        user.setEmail(insertDto.getEmail());
        user.setLocation(insertDto.getLocation());
        user.setAbout(insertDto.getAbout());
        user.setPassword(insertDto.getPassword());
        user.setRole(UserRole.USER);

        userService.save(user);

        return ok(user);
    }

    @ApiOperation(
            value = "Log out user",
            notes = "Logs user out using authorization token",
            httpMethod = "GET"
    )
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(
                            name = "X-Auth-Token",
                            dataType = "string",
                            required = true,
                            paramType = "header",
                            value = "Authorization header"
                    )
            }
    )
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseWrapper logout(@RequestHeader("Authorization") String authorizationToken) {
        authenticationService.logout(authorizationToken);
        return noContent();
    }



}
