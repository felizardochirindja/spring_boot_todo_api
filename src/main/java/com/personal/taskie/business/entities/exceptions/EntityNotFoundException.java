package com.personal.taskie.business.app.exceptions;

public final class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
