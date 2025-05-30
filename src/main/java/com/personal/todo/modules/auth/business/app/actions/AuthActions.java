package com.personal.todo.modules.auth.business.app.actions;

import com.personal.todo.modules.user.adapters.repositories.UserRepository;
import com.personal.todo.modules.auth.business.app.actions.params.input.SignupInput;
import com.personal.todo.modules.user.business.app.ports.input.UserCreator;
import com.personal.todo.modules.auth.business.app.actions.ports.output.TokenGenerator;
import com.personal.todo.modules.auth.business.entities.AuthUser;
import com.personal.todo.modules.user.business.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public final class AuthActions {
    @Autowired
    private UserCreator createUserAction;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenGenerator tokenGenerator;
    @Autowired
    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(AuthActions.class);

    public User signUp(SignupInput params) {
        logger.atInfo()
                .setMessage("Signing up user!")
                .addKeyValue("email", params.email())
                .log();

        String passwordHash = passwordEncoder.encode(params.password());
        User createdUser = createUserAction.execute(params.createUserInput(passwordHash));

        logger.atInfo()
                .setMessage("Sign up completed!")
                .addKeyValue("userId", createdUser.getId())
                .addKeyValue("email", createdUser.getEmail())
                .log();

        return createdUser;
    }

    public String login(String email, String password) {
        logger.atInfo()
                .setMessage("Login attempt!")
                .addKeyValue("email", email)
                .log();

        var authManager = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

        String token = tokenGenerator.generateToken((AuthUser) authManager.getPrincipal());

        userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.atWarn()
                            .setMessage("User not found after authentication!")
                            .addKeyValue("email", email)
                            .log();

                    return new UsernameNotFoundException("User not found!");
                });

        logger.atInfo()
                .setMessage("Login successful!")
                .addKeyValue("email", email)
                .log();

        return token;
    }
}
