package com.personal.todoapp.modules.auth.business.app.actions.params.input;

import com.personal.todoapp.modules.user.business.app.params.input.CreateUserInput;
import com.personal.todoapp.modules.user.business.entities.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SignupInput(
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
        public CreateUserInput createUserInput(String passwordHash) {
                return new CreateUserInput(name, email, passwordHash, role);
        }
}
