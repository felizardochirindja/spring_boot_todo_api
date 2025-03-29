package com.personal.todo.business.app.params;

public record CreateUserParams(
    String name,
    String email,
    String password
) {
}
