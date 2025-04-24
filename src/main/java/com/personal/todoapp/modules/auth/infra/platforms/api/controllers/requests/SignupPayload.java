package com.personal.todoapp.modules.auth.infra.platforms.api.controllers.requests;

import com.personal.todoapp.modules.auth.business.app.actions.params.input.SignupInput;
import com.personal.todoapp.modules.user.business.entities.Role;
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
