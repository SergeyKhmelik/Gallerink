package controller;

import org.springframework.http.HttpStatus;

public enum  RequestError {

    NO_USER_FOUND(HttpStatus.NOT_FOUND, 400001, "No user with such email");

    private HttpStatus status;
    private int errorCode;
    private String message;

    private RequestError(HttpStatus status, int errorCode, String message) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
