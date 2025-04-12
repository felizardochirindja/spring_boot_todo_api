package com.personal.taskie.business.app.params;

import com.personal.taskie.business.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserInput(
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
    public User createUser() {
        return new User(name, email, password);
    }
}
