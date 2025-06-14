package com.personal.todo.modules.auth.infra.platforms.api.controllers.v1;

import com.personal.todo.modules.auth.business.app.actions.AuthActions;
import com.personal.todo.modules.auth.business.app.actions.params.input.SignupInput;
import com.personal.todo.modules.user.business.entities.User;
import com.personal.todo.modules.auth.infra.platforms.api.controllers.v1.requests.LoginPayload;
import com.personal.todo.modules.auth.infra.platforms.api.controllers.v1.requests.SignupPayload;
import com.personal.todo.modules.auth.infra.platforms.api.controllers.v1.responses.LoginApiResponse;
import com.personal.todo.modules.user.infra.platforms.api.controllers.v1.responses.UserApiResponse;
import com.personal.todo.modules.user.infra.platforms.api.controllers.v1.responses.UserApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth", produces = {"application/json"})
public final class AuthController {
    @Autowired
    private AuthActions authActions;

    @PostMapping("/login")
    @Operation(
            summary = "efetuar o login",
            method = "POST",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "login efetuado",
                            content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = LoginApiResponse.class)
                            )
                    ),
            }
    )
    public ResponseEntity<LoginApiResponse> login(@RequestBody @Valid LoginPayload payload) {
        String token = authActions.login(payload.email(), payload.password());
        return ResponseEntity.ok(new LoginApiResponse(token));
    }

    @PostMapping("/signup")
    @Operation(
            summary = "cadastrar usuario",
            method = "POST",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "usuario registrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserApiResponse.class)
                            )
                    )
            }
    )
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
