package com.banquemisr.challenge05.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends RuntimeException {
    private HttpStatus status;


    public ConflictException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    {
    }
}
