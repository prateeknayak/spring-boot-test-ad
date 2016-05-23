package com.example.springboot.ad.exceptions;


public class FailedAuthenticationException extends Exception {
    public FailedAuthenticationException() {
        super("Failed to authenticate user.");
    }

    public FailedAuthenticationException(String message) {
        super(message);
    }

    public FailedAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
