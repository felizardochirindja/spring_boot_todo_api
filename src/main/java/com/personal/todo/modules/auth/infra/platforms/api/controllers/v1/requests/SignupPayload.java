package com.personal.todo.modules.auth.infra.platforms.api.controllers.v1.requests;

import com.personal.todo.modules.auth.business.app.actions.params.input.SignupInput;
import com.personal.todo.modules.user.business.entities.Role;
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
