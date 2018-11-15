package com.github.bysrkh.rmsboot.util.error;

public class DuplicateEntityException extends RuntimeException {
    private String[] properties;

    public DuplicateEntityException(String message, String ...properties) {
        super(message);

        this.properties = properties;
    }

    public String[] getProperties() {
        return properties;
    }
}
