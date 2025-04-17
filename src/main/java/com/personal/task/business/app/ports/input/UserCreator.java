package com.personal.task.business.app.ports.input;

import com.personal.task.business.app.params.input.CreateUserInput;
import com.personal.task.business.entities.User;

public interface UserCreator {
    User execute(CreateUserInput params);
}
