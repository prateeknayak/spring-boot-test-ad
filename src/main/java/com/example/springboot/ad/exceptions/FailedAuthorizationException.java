package com.example.springboot.ad.exceptions;


public class FailedAuthorizationException extends Exception {
    public FailedAuthorizationException() {
        super("User not authorized to access this resource.");
    }

    public FailedAuthorizationException(String message) {
        super(message);
    }

    public FailedAuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
