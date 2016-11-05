package controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import rest.MetadataWrapper;
import rest.ResponseWrapper;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(BaseException.class)
    public @ResponseBody ResponseWrapper handleCustomException(HttpServletResponse resp, BaseException ex) {
        MetadataWrapper metadata = new MetadataWrapper(ex.getStatus().value(), ex.getErrorCode(), ex.getStatus().getReasonPhrase(), ex.getMessage());
        resp.setStatus(ex.getStatus().value());
        return new ResponseWrapper(metadata);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseWrapper handleBadCredentialsException(HttpServletResponse resp, BadCredentialsException ex) {
        return handleCustomException(resp, new BaseException(RequestError.WRONG_CREDENTIALS));
    }

    @ExceptionHandler(Exception.class)
    public @ResponseBody ResponseWrapper handleGeneralException(Exception ex){
        ex.printStackTrace();
        MetadataWrapper metadata = new MetadataWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), ex.getMessage());
        return new ResponseWrapper(metadata);
    }

    @ExceptionHandler(Error.class)
    public @ResponseBody ResponseWrapper handleUnsupportableError(Error er){
        er.printStackTrace();
        MetadataWrapper metadata = new MetadataWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), er.getMessage());
        return new ResponseWrapper(metadata);
    }

}