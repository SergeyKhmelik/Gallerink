package rest;

import org.springframework.http.HttpStatus;

public class MetadataWrapper {

    private int code;
    private int errorCode;
    private String userMessage;
    private String developerMessage;

    public MetadataWrapper(int code, int errorCode) {
        this(code, errorCode, (String) null, (String) null);
    }

    public MetadataWrapper(int code, int errorCode, String userMessage) {
        this(code, errorCode, userMessage, (String) null);
    }

    public MetadataWrapper(int code, int errorCode, String userMessage, String developerMessage) {
        this.code = code;
        this.errorCode = errorCode;
        this.userMessage = userMessage;
        this.developerMessage = developerMessage;
    }

    public MetadataWrapper(HttpStatus status) {
        this.code = status.value();
        this.userMessage = status.getReasonPhrase();
        this.developerMessage = status.getReasonPhrase();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

}
