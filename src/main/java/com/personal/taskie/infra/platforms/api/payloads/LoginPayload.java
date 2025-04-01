package com.personal.taskie.infra.platforms.api.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginPayload(
        @NotNull
        @NotBlank
        String email,
        @NotNull
        @NotBlank
        String password
) {
}
