package com.personal.taskie.infra.platforms.api.responses;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UserApiResponse(
        @NotNull
        String status,
        @NotNull
        String message,
        @NotNull
        @Valid
        UserResponse user
) {
}
