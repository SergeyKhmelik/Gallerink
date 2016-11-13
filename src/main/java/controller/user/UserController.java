package controller.user;

import controller.BaseController;
import controller.BaseException;
import controller.RequestError;
import domain.user.User;
import dto.user.PasswordChangeDto;
import dto.user._SimpleUser;
import dto.user._User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.*;
import rest.ResponseWrapper;
import security.AuthenticationService;
import security.TokenManager;
import service.user.UserService;

import javax.naming.AuthenticationException;
import java.util.Objects;

@Api(value = "/api/user_", description = "Provides methods for user flow")
@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @ApiOperation(
            value = "Get user profile",
            notes = "Gets user profile by authorization token",
            response = _User.class,
            httpMethod = "GET"
    )
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(
                            name = "X-Auth-Token",
                            dataType = "string",
                            required = true,
                            paramType = "header",
                            value = "Authorization token"
                    )
            }
    )
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ResponseWrapper getProfile() throws BaseException {
        User user = getUserByToken();

        return ok(new _User(user));
    }

    @ApiOperation(
            value = "Updates user profile",
            notes = "Updates user profile by authorization token",
            response = _SimpleUser.class,
            httpMethod = "PUT"
    )
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(
                            name = "X-Auth-Token",
                            dataType = "string",
                            required = true,
                            paramType = "header",
                            value = "Authorization token"
                    )
            }
    )
    @RequestMapping(value = "/profile", method = RequestMethod.PUT)
    public ResponseWrapper updateProfile(@RequestBody _SimpleUser updateDto) throws BaseException {
        User user = getUserByToken();
        user.setName(updateDto.getName());
        user.setLocation(updateDto.getLocation());
        user.setAbout(updateDto.getAbout());
        user.setAvatar(updateDto.getAvatar());
        userService.save(user);

        return ok(new _SimpleUser(user));
    }

    @ApiOperation(
            value = "Change user password",
            notes = "Changes user password by authorization token",
            httpMethod = "POST"
    )
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(
                            name = "X-Auth-Token",
                            dataType = "string",
                            required = true,
                            paramType = "header",
                            value = "Authorization token"
                    )
            }
    )
    @RequestMapping(value = "/profile/passwordChange", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseWrapper changePassword(@RequestBody PasswordChangeDto passDto) throws BaseException {
        User user = getUserByToken();

        if (!user.getPassword().equals(passDto.getOldPassword())) {
            throw new BaseException(RequestError.WRONG_OLD_PASSWORD);
        }

        user.setPassword(passDto.getNewPassword());
        userService.save(user);

        return noContent();
    }

    private User getUserByToken() throws BaseException {
        if (authenticationService.currentUser() == null) {
            throw new BaseException(RequestError.AUTHORIZATION_REQUIRED);
        }

        return userService.getUserByEmail(authenticationService.currentUser().getUsername());
    }

}
