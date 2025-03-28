package com.personal.todo.infra.web.controllers.responses;

import com.personal.todo.business.entities.User;

public record CreateUserResponse(
        String status,
        String message,
        User user
) {
}
