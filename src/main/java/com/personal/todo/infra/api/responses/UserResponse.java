package com.personal.todo.infra.api.responses;

import com.personal.todo.business.entities.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserResponse {
    private String name;
    private String email;

    public static UserResponse fromEntity(User user) {
        return new UserResponse(user.getName(), user.getEmail());
    }
}
