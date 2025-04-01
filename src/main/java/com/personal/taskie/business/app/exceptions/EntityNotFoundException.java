package com.personal.todo.business.app.exceptions;

public final class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
