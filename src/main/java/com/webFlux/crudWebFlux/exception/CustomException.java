package com.webFlux.crudWebFlux.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {
    private HttpStatus status;

    public HttpStatus getStatus() {
        return status;
    }

    public CustomException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }


}
