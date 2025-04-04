package com.personal.taskie.infra.platforms.api.controllers;

import com.personal.taskie.adapters.repos.UserRepository;
import com.personal.taskie.business.app.actions.AuthActions;
import com.personal.taskie.business.app.params.SignupParams;
import com.personal.taskie.business.app.ports.output.TokenGenerator;
import com.personal.taskie.business.entities.AuthUser;
import com.personal.taskie.business.entities.User;
import com.personal.taskie.infra.platforms.api.payloads.LoginPayload;
import com.personal.taskie.infra.platforms.api.payloads.SignupPayload;
import com.personal.taskie.infra.platforms.api.responses.LoginResponse;
import com.personal.taskie.infra.platforms.api.responses.UserApiResponse;
import com.personal.taskie.infra.platforms.api.responses.UserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private UserRepository userRepository;
    @Autowired
    private TokenGenerator tokenGenerator;
    @Autowired
    private AuthActions authActions;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginPayload payload) {
        var authManager = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    payload.email(),
                    payload.password()
                )
        );

        String token = tokenGenerator.generateToken((AuthUser) authManager.getPrincipal());

        userRepository.findByEmail(payload.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserApiResponse> signup(@RequestBody @Valid SignupPayload payload) {
        SignupParams params = payload.createActionParams();
        User user = authActions.signUp(params);

        var response = new UserApiResponse(
                "sucess",
                "user created successfully",
                UserResponse.fromEntity(user)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
