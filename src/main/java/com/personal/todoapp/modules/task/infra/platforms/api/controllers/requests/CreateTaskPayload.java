package com.personal.todoapp.modules.task.infra.platforms.api.controllers.requests;

import com.personal.todoapp.modules.task.business.app.params.input.CreateTaskInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTaskPayload(
        @NotNull(message = "title cannot be null")
        @NotBlank
        String title,
        @NotNull(message = "userId cannot be null")
        Integer userId
) {
        public CreateTaskInput createActionParams() {
                return new CreateTaskInput(title, userId);
        }
}
