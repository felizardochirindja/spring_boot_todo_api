package com.personal.taskie.infra.platforms.api.payloads;

import com.personal.taskie.business.app.params.CreateUserParams;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserPayload(
        @NotNull(message = "name cannot be null")
        @NotBlank
        String name,
        @NotNull(message = "email cannot be null")
        @NotBlank
        String email,
        @NotNull(message = "password cannot be null")
        @NotBlank
        String password
) {
        public CreateUserParams createActionParams() {
                return new CreateUserParams(name, email, password);
        }
}
