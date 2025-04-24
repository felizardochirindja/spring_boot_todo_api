package com.personal.todoapp.modules.shared.exceptions;

public final class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
