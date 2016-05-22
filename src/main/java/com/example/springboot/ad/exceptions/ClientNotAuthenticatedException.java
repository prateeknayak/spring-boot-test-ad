package com.example.springboot.ad.exceptions;


public class ClientNotAuthenticatedException extends Exception {
    public ClientNotAuthenticatedException() {
        super("Client not authenticated, Please login again.");
    }

    public ClientNotAuthenticatedException(String message) {
        super(message);
    }

    public ClientNotAuthenticatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
