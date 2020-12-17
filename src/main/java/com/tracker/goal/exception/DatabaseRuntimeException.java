package com.tracker.goal.exception;

public class DatabaseRuntimeException extends RuntimeException {

    private String message;

    public DatabaseRuntimeException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
