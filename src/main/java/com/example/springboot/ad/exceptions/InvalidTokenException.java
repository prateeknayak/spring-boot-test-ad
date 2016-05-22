package com.example.springboot.ad.exceptions;

public class InvalidTokenException  extends Exception {

    public InvalidTokenException() {
        super("Invalid token exception, need to login again");
    }

    public InvalidTokenException(String message) {
        super(message);
    }

    public InvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
