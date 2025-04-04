package com.personal.taskie.business.app.actions;

import com.personal.taskie.business.app.params.CreateUserParams;
import com.personal.taskie.business.app.params.SignupParams;
import com.personal.taskie.business.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public final class AuthActions {
    @Autowired
    private UserActions userActions;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User signUp(SignupParams params) {
        String passwordHash = passwordEncoder.encode(params.password());

        return userActions.create(new CreateUserParams(
                params.name(),
                params.email(),
                passwordHash
        ));
    }
}
