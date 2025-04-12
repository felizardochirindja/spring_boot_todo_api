package com.personal.taskie.business.types;

public enum TaskStatus {
    COMPLETED("completa"),
    PENDING("pendente");

    public final String status;

    TaskStatus(String status) {
        this.status = status;
    }
}
