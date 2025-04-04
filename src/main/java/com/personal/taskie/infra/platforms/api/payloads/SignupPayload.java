package com.personal.taskie.infra.platforms.api.payloads;

import com.personal.taskie.business.app.params.SignupParams;
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
        String password
) {
        public SignupParams createActionParams() {
                return new SignupParams(name, email, password);
        }
}
