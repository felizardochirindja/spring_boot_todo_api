package com.personal.taskie.infra.platforms.api.responses;

import com.personal.taskie.business.entities.User;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserApi {
    @NotNull
    private String name;
    @NotNull
    private String email;

    public static UserApi fromEntity(User user) {
        return new UserApi(user.getName(), user.getEmail());
    }
}
