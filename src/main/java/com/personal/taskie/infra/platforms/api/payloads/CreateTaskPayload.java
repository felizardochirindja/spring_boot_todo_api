package com.personal.taskie.infra.platforms.api.payloads;

import com.personal.taskie.business.app.params.input.CreateTaskInput;
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
