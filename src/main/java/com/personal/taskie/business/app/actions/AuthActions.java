package com.personal.taskie.business.app.actions;

import com.personal.taskie.adapters.repos.UserRepository;
import com.personal.taskie.business.app.params.input.SignupInput;
import com.personal.taskie.business.app.ports.input.UserCreator;
import com.personal.taskie.business.app.ports.output.TokenGenerator;
import com.personal.taskie.business.entities.AuthUser;
import com.personal.taskie.business.entities.User;
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
        var authManager = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

        String token = tokenGenerator.generateToken((AuthUser) authManager.getPrincipal());

        userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return token;
    }
}
