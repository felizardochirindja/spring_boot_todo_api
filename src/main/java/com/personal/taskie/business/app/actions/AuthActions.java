package com.personal.taskie.business.app.actions;

import com.personal.taskie.adapters.repos.UserRepository;
import com.personal.taskie.business.app.params.CreateUserParams;
import com.personal.taskie.business.app.params.SignupParams;
import com.personal.taskie.business.app.ports.output.TokenGenerator;
import com.personal.taskie.business.entities.AuthUser;
import com.personal.taskie.business.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public final class AuthActions {
    @Autowired
    private UserActions userActions;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenGenerator tokenGenerator;
    @Autowired
    private UserRepository userRepository;

    public User signUp(SignupParams params) {
        String passwordHash = passwordEncoder.encode(params.password());

        return userActions.create(new CreateUserParams(
                params.name(),
                params.email(),
                passwordHash
        ));
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
