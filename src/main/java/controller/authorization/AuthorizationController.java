package controller.authorization;


import controller.BaseController;
import controller.BaseException;
import controller.RequestError;
import domain.user.User;
import domain.user.UserRole;
import dto.auth.SignInDto;
import dto.user.EmailInsertDto;
import dto.user._User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public ResponseWrapper signIn(@RequestBody SignInDto signInDto, HttpServletResponse response) throws BaseException {
        addToken(signInDto.getEmail(), signInDto.getPassword(), response);
        User user = userService.getUserByEmail(signInDto.getEmail());
        return ok(new _User(user));
    }

    @ApiOperation(
            value = "Sign up user",
            notes = "Signs user up by dto",
            response = _User.class,
            httpMethod = "POST"
    )
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseWrapper signUp(@RequestBody _User insertDto, HttpServletResponse response) throws BaseException {
        User user = new User();
        user.setEmail(insertDto.getEmail());
        user.setLocation(insertDto.getLocation());
        user.setAbout(insertDto.getAbout());
        user.setPassword(insertDto.getPassword());
        user.setName(insertDto.getName());
        user.setAvatar(insertDto.getAvatar());
        user.setRole(UserRole.USER);
        userService.save(user);

        addToken(user.getEmail(), user.getPassword(), response);

        return ok(new _User(user));
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
    public ResponseWrapper logout(@RequestHeader("X-Auth-Token") String authorizationToken) {
        authenticationService.logout(authorizationToken);
        return noContent();
    }

    @ApiOperation(
            value = "Checks whether email is already used",
            notes = "Returns true if user email is already used and false if email is free.",
            httpMethod = "POST"
    )
    @RequestMapping(value = "/emailExist", method = RequestMethod.POST)
    public ResponseWrapper emailExist(@RequestBody EmailInsertDto emailInsertDto) {
        return ok(userService.getUserByEmail(emailInsertDto.getEmail()) != null);
    }

    @ApiOperation(
            value = "Resets user password",
            notes = "Resets user password and sends a message on an email",
            httpMethod = "POST"
    )
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public String resetPassword(@RequestBody EmailInsertDto emailInsertDto) {
        return System.getProperty("user.dir");
    }

    private void addToken(String email, String password, HttpServletResponse response) throws BaseException {
        TokenInfo token = authenticationService.authenticate(email, password);
        response.setHeader("X-Auth-Token", token.getToken());
    }

}
