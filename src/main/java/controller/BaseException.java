package controller;

import org.springframework.http.HttpStatus;

public class BaseException extends Exception {

    private HttpStatus status;

    private int errorCode;

    private String message;

    public BaseException(RequestError error) {
        setStatus(error.getStatus());
        setErrorCode(error.getErrorCode());
        setMessage(error.getMessage());
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
