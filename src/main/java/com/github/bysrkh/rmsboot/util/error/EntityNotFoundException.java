package com.github.bysrkh.rmsboot.util.error;

public class EntityNotFoundException extends RuntimeException {
    private String message;

    public EntityNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
