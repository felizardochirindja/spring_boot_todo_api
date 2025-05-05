package com.personal.todoapp.modules.user.infra.platforms.api.controllers.v1.responses;

import com.personal.todoapp.modules.user.business.entities.User;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public final class UserApi {
    @NotNull
    private String name;
    @NotNull
    private String email;

    public static UserApi fromEntity(User user) {
        return new UserApi(user.getName(), user.getEmail());
    }
}
