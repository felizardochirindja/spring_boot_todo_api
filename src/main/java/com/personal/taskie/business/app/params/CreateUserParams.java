package com.personal.taskie.business.app.params;

import com.personal.taskie.business.entities.User;
import jakarta.validation.constraints.NotNull;

public record CreateUserParams (
        @NotNull
        String name,
        @NotNull
        String email,
        @NotNull
        String password
) {
    public User createUser() {
        return new User(name, email, password);
    }
}
