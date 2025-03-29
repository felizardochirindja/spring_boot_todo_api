package com.personal.todo.business.types;

public enum TodoStatus {
    COMPLETED("completa"),
    PENDING("pendente");

    String status;

    TodoStatus(String status) {
        this.status = status;
    }
}
