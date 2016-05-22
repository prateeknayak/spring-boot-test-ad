package com.example.springboot.ad.exceptions;

/**
 * Brief description of the file
 * <p>
 * Now a decent description of what this file is for. A paragraph at least.
 * <p>
 * Copyright © Tabcorp Pty Ltd. All rights reserved. http://www.Tabcorp.com/
 * This code is copyrighted and is the exclusive property of Tabcorp Pty Ltd. It may not be used, copied or redistributed without the written permission of Tabcorp.
 *
 * @author Prateek Nayak <prateek.nayak@tabcorp.com.au>
 */
public class TokenAlreadyExistsException extends Exception {
    public TokenAlreadyExistsException() {
        super("Token already exists in the store, generate a new token");
    }

    public TokenAlreadyExistsException(String message) {
        super(message);
    }

    public TokenAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
