package com.osipchik.service.exeption;

public class ResourceNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(Exception e) {
        super(e);
    }

    public ResourceNotFoundException(String message, Exception e) {
        super(message, e);
    }
}
