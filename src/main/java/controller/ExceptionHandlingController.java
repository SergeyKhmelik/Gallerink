package controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import rest.MetadataWrapper;
import rest.ResponseWrapper;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(BaseException.class)
    public @ResponseBody ResponseWrapper handleCustomException(BaseException ex) {
        MetadataWrapper metadata = new MetadataWrapper(ex.getStatus().value(), ex.getErrorCode(), ex.getStatus().getReasonPhrase(), ex.getMessage());
        return new ResponseWrapper(metadata);
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