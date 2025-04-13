package com.personal.taskie.business.app.actions;

import com.personal.taskie.adapters.repos.UserRepository;
import com.personal.taskie.business.app.params.input.CreateUserInput;
import com.personal.taskie.business.app.params.input.SignupInput;
import com.personal.taskie.business.app.ports.input.UserCreator;
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
    private UserCreator createUserAction;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenGenerator tokenGenerator;
    @Autowired
    private UserRepository userRepository;

    public User signUp(SignupInput params) {
        String passwordHash = passwordEncoder.encode(params.password());
        return createUserAction.execute(params.createUserInput(passwordHash));
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
