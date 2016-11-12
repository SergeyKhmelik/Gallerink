package controller;

import org.springframework.http.HttpStatus;

public enum  RequestError {

    AUTHORIZATION_REQUIRED(HttpStatus.UNAUTHORIZED, 401000, "Authorization required"),
    WRONG_CREDENTIALS(HttpStatus.BAD_REQUEST, 400000, "Wrong credentials"),
    WRONG_OLD_PASSWORD(HttpStatus.UNAUTHORIZED, 401002, "Wrong old password provided"),
    USERNAME_ALREADY_USED(HttpStatus.BAD_REQUEST, 400001, "User with this username already exists"),
    FILE_REQUIRED(HttpStatus.BAD_REQUEST, 400003, "File required"),
    FILE_FORMAT_EXCEPTION(HttpStatus.BAD_REQUEST, 400002, "File format should be .jpg, .png, .tiff, .gif"),
    IMAGE_UPLOAD_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500001, "File upload exception"),
    FILE_NOT_FOUND(HttpStatus.NOT_FOUND, 404001, "File not found");


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
