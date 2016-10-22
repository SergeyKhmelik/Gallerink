package controller.user;

import controller.BaseController;
import dto.user._User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rest.ResponseWrapper;
import security.AuthenticationService;
import security.TokenManager;

@Api(value = "/api/user_", description = "Provides methods for user flow")
@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {

    @Autowired
    private AuthenticationService authenticationService;

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
    public ResponseWrapper getProfile() {
        return ok(authenticationService.currentUser());
    }

    @ApiOperation(
            value = "Resets user password",
            notes = "Resets user password and sends a message on an email",
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
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void resetPassword (String email) {

    }


}
