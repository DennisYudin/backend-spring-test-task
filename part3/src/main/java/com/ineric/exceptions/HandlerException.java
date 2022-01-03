package com.ineric.exceptions;

public class HandlerException extends RuntimeException {

    public HandlerException() {
        super();
    }

    public HandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public HandlerException(String message) {
        super(message);
    }
}
