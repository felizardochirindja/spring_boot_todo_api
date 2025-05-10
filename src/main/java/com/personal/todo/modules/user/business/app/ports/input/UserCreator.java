package com.personal.todo.modules.user.business.app.ports.input;

import com.personal.todo.modules.user.business.app.params.input.CreateUserInput;
import com.personal.todo.modules.user.business.entities.User;

public interface UserCreator {
    User execute(CreateUserInput params);
}
