package com.personal.todo.business.types;

public enum TodoStatus {
    COMPLETED("completa"),
    PENDING("pendente");

    public final String status;

    TodoStatus(String status) {
        this.status = status;
    }
}
