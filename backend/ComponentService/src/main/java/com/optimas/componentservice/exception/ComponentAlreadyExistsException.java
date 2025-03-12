package com.optimas.componentservice.exception;

public class ComponentAlreadyExistsException extends RuntimeException {
    public ComponentAlreadyExistsException(String message) {
        super(message);
    }
}
