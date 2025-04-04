package com.personal.taskie.business.app.params;

import jakarta.validation.constraints.NotNull;

public record SignupParams(
        @NotNull
        String name,
        @NotNull
        String email,
        @NotNull
        String password
) {
}
