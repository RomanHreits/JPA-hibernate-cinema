package com.cinema.exceptions;

public class DataProcessingException extends RuntimeException {
    private String message;

    public DataProcessingException(String message) {
        super(message);
    }

    public DataProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
