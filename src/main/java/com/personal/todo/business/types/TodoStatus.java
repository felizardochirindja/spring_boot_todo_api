package com.personal.todo.business.types;

public enum TodoStatus {
    COMPLETED("completa"),
    PENDENDING("pendente");

    String status;

    TodoStatus(String status) {
        this.status = status;
    }
}
