package com.personal.todoapp.modules.user.business.app.params.input;

import com.personal.todoapp.modules.user.business.entities.Role;
import com.personal.todoapp.modules.user.business.entities.User;
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
        String password,
        @NotNull
        Role.Values role
) {
    public User createUser(Role role) {
        return new User(name, email, password, role);
    }
}
