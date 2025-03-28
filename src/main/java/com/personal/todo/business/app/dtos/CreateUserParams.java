package com.personal.todo.business.app.dtos;

public record CreateUserParams(
    String name,
    String email,
    String password
) {
}
