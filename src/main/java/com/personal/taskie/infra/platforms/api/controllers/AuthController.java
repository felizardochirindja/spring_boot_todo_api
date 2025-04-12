package com.personal.taskie.infra.platforms.api.controllers;

import com.personal.taskie.business.app.actions.AuthActions;
import com.personal.taskie.business.app.params.input.SignupInput;
import com.personal.taskie.business.entities.User;
import com.personal.taskie.infra.platforms.api.payloads.LoginPayload;
import com.personal.taskie.infra.platforms.api.payloads.SignupPayload;
import com.personal.taskie.infra.platforms.api.responses.LoginApiResponse;
import com.personal.taskie.infra.platforms.api.responses.UserApiResponse;
import com.personal.taskie.infra.platforms.api.responses.UserApi;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public final class AuthController {
    @Autowired
    private AuthActions authActions;

    @PostMapping("/login")
    public ResponseEntity<LoginApiResponse> login(@RequestBody @Valid LoginPayload payload) {
        String token = authActions.login(payload.email(), payload.password());
        return ResponseEntity.ok(new LoginApiResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserApiResponse> signup(@RequestBody @Valid SignupPayload payload) {
        SignupInput params = payload.createActionParams();
        User user = authActions.signUp(params);

        var response = new UserApiResponse(
                "sucess",
                "user created successfully",
                UserApi.fromEntity(user)
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
