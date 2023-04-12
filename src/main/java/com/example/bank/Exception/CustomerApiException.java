package com.example.bank.Exception;

import org.springframework.http.HttpStatus;

public class CustomerApiException extends  RuntimeException{
    private HttpStatus status;

    private String message;

    public CustomerApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public CustomerApiException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public CustomerApiException(String message, Throwable cause, HttpStatus status, String message1) {
        super(message, cause);
        this.status = status;
        this.message = message1;
    }

    public CustomerApiException(Throwable cause, HttpStatus status, String message) {
        super(cause);
        this.status = status;
        this.message = message;
    }

    public CustomerApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatus status, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
