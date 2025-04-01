package com.personal.taskie.infra.platforms.api.payloads;

import com.personal.taskie.business.app.params.CreateTodoParams;
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
