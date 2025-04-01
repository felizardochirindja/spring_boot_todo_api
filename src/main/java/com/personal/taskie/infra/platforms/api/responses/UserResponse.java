package com.personal.todo.infra.platforms.api.responses;

import com.personal.todo.business.entities.User;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserResponse {
    @NotNull
    private String name;
    @NotNull
    private String email;

    public static UserResponse fromEntity(User user) {
        return new UserResponse(user.getName(), user.getEmail());
    }
}
