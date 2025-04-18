package com.personal.task.business.app.params.input;

import com.personal.task.business.entities.Role;
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
