package com.personal.todo.infra.api.responses;

public record UserApiResponse(
        String status,
        String message,
        UserResponse user
) {
}
