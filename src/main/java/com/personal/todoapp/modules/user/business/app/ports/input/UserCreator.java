package com.personal.todoapp.modules.user.business.app.ports.input;

import com.personal.todoapp.modules.user.business.app.params.input.CreateUserInput;
import com.personal.todoapp.modules.user.business.entities.User;

public interface UserCreator {
    User execute(CreateUserInput params);
}
