package com.personal.todo.modules.auth.business.app.actions;

import com.personal.todo.modules.auth.business.app.actions.params.input.SignupInput;
import com.personal.todo.modules.auth.business.app.actions.ports.output.TokenGenerator;
import com.personal.todo.modules.auth.business.entities.AuthUser;
import com.personal.todo.modules.user.adapters.repositories.UserRepository;
import com.personal.todo.modules.user.business.app.CreateUserAction;
import com.personal.todo.modules.user.business.app.UserActions;
import com.personal.todo.modules.user.business.app.params.input.CreateUserInput;
import com.personal.todo.modules.user.business.entities.Role;
import com.personal.todo.modules.user.business.entities.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
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
    @MockitoBean
    private CreateUserAction createUserAction;
    @InjectMocks
    private AuthActions authActions;

    @Test
    void userShouldSignupSuccessfully() {
        // Arrange
        String name = "felix";
        String email = "felix@gmail.com";
        String rawPassword = "1234";
        String hashedPassword = "hashed_1234";

        SignupInput signupInput = new SignupInput(name, email, rawPassword, Role.Values.ADMIN);
        CreateUserInput createUserInput = signupInput.createUserInput(hashedPassword);

        Role role = new Role(Role.Values.ADMIN, "administrator");
        User expectedUser = new User(name, email, hashedPassword, role);

        Mockito.when(passwordEncoder.encode(rawPassword)).thenReturn(hashedPassword);
        Mockito.when(createUserAction.execute(createUserInput)).thenReturn(expectedUser);

        // Act
        User createdUser = authActions.signUp(signupInput);

        // Assert
        assertEquals(expectedUser, createdUser);
        Mockito.verify(passwordEncoder).encode(rawPassword);
        Mockito.verify(createUserAction).execute(createUserInput);
    }

    @Test
    void userShouldLoginSuccessfully() {
        // Arrange
        String email = "felix@gmail.com";
        String password = "password";
        String expectedToken = "jwt-token";

        AuthUser authUser = new AuthUser();
        var auth = Mockito.mock(Authentication.class);
        var authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        Mockito.when(auth.getPrincipal()).thenReturn(authUser);
        Mockito.when(authenticationManager.authenticate(authenticationToken)).thenReturn(auth);
        Mockito.when(tokenGenerator.generateToken(authUser)).thenReturn(expectedToken);
        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User()));

        // Act
        String result = authActions.login(email, password);

        // Assert
        assertEquals(expectedToken, result);

        Mockito.verify(authenticationManager).authenticate(authenticationToken);
        Mockito.verify(tokenGenerator).generateToken(authUser);
        Mockito.verify(userRepository).findByEmail(email);
    }

    @Test
    void loginShouldFailWhenUserNotFound()
    {
        // Arrange
        String email = "felix@gmail.com";
        String password = "password";
        String expectedToken = "jwt-token";

        AuthUser authUser = new AuthUser();
        var auth = Mockito.mock(Authentication.class);
        var authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        Mockito.when(auth.getPrincipal()).thenReturn(authUser);
        Mockito.when(authenticationManager.authenticate(authenticationToken)).thenReturn(auth);
        Mockito.when(tokenGenerator.generateToken(authUser)).thenReturn(expectedToken);
        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & assert
        assertThrows(UsernameNotFoundException.class, () -> authActions.login(email, password));
    }
}