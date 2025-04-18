package com.personal.task.infra.platforms.api.payloads;

import com.personal.task.business.app.params.input.SignupInput;
import com.personal.task.business.entities.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SignupPayload(
        @NotNull
        @NotBlank
        String name,
        @NotNull
        @NotBlank
        String email,
        @NotNull
        @NotBlank
        String password,
        @NotNull
        Role.Values role
) {
        public SignupInput createActionParams() {
                return new SignupInput(name, email, password, role);
        }
}
