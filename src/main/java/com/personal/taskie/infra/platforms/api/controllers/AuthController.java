package com.personal.todo.infra.platforms.api.controllers;

import com.personal.todo.adapters.repos.UserRepository;
import com.personal.todo.business.app.ports.output.TokenGenerator;
import com.personal.todo.business.entities.User;
import com.personal.todo.infra.platforms.api.payloads.LoginPayload;
import com.personal.todo.infra.platforms.api.payloads.SignupPayload;
import com.personal.todo.infra.platforms.api.responses.LoginResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public final class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenGenerator tokenGenerator;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginPayload payload) {
        var authManager = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    payload.email(),
                    payload.password()
                )
        );

        String token = tokenGenerator.generateToken((User) authManager.getPrincipal());

        userRepository.findByEmail(payload.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody SignupPayload payload) {
        if(userRepository.findByEmail(payload.email()).isPresent())
            return ResponseEntity.badRequest().build();

        String passwordHash = passwordEncoder.encode(payload.password());
        User user = new User(payload.name(), payload.email(), passwordHash);

        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}
