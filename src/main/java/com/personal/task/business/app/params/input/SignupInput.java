package com.personal.task.business.app.params.input;

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
        String password
) {
        public CreateUserInput createUserInput(String passwordHash) {
                return new CreateUserInput(name(), email(), passwordHash);
        }
}
