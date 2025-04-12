package com.personal.taskie.business.app.ports.input;

import com.personal.taskie.business.app.params.CreateUserInput;
import com.personal.taskie.business.entities.User;

public interface UserCreator {
    User execute(CreateUserInput params);
}
