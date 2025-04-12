package com.personal.taskie.business.app.actions;

import com.personal.taskie.adapters.repos.UserRepository;
import com.personal.taskie.business.app.ports.output.TokenGenerator;
import org.mockito.InjectMocks;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;

class AuthActionsTest {
    @MockitoBean
    private UserActions userActions;
    @MockitoBean
    private PasswordEncoder passwordEncoder;
    @MockitoBean
    private AuthenticationManager authenticationManager;
    @MockitoBean
    private TokenGenerator tokenGenerator;
    @MockitoBean
    private UserRepository userRepository;
    @InjectMocks
    AuthActions authActions;

    void userShouldLoginSuccessfully() {

    }
}