package com.personal.taskie.business.entities.exceptions;

public final class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
