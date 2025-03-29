package com.personal.todo.infra.web.controllers.payloads;

import com.personal.todo.business.app.dtos.CreateTodoParams;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTodoPayload(
        @NotNull(message = "title cannot be null")
        @NotBlank
        String title,
        @NotNull(message = "userId cannot be null")
        Integer userId
) {
        public CreateTodoParams createActionParams() {
                return new CreateTodoParams(title, userId);
        }
}
