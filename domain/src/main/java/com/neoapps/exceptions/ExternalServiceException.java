package com.neoapps.exceptions;

public class ExternalServiceException extends RuntimeException {

    public ExternalServiceException(String message, Throwable cause) {
        super(message + ".  " + (cause.getCause() != null ? cause.getCause().getMessage() : ""));
    }
}
