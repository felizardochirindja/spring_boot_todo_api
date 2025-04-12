package com.personal.taskie.business.app.actions;

import com.personal.taskie.adapters.repos.UserRepository;
import com.personal.taskie.business.entities.exceptions.EntityNotFoundException;
import com.personal.taskie.business.app.params.CreateUserInput;
import com.personal.taskie.business.entities.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserActionsTest {
    @MockitoBean
    private UserRepository userRepository;
    @InjectMocks
    UserActions userActions;

    @Test
    void shouldCreateUserSuccessfully() {
        // arrange
        String userEmail = "felix@gmail.com";

        var params = mock(CreateUserInput.class);
        User expectedUser = new User("felix", "felix@gmail.com", "1234");

        when(params.email()).thenReturn(userEmail);
        when(params.createUser()).thenReturn(expectedUser);

        when(userRepository.findByEmail(userEmail)).thenReturn(Optional.empty());
        when(userRepository.save(expectedUser)).thenReturn(expectedUser);

        // act
        User createdUser = userActions.create(params);

        // assert
        assertEquals(expectedUser, createdUser);
        verify(userRepository).findByEmail(userEmail);
        verify(userRepository).save(expectedUser);
    }

    @Test
    void createShouldThrowExceptionWhenUserAlreadyExists() {
        // arrange
        var params = mock(CreateUserInput.class);
        String userEmail = "felix@gmail.com";

        when(params.email()).thenReturn(userEmail);
        when(userRepository.findByEmail(userEmail)).thenReturn(Optional.of(new User()));

        // act & assert
        assertThrows(RuntimeException.class, () -> userActions.create(params));
        verify(userRepository).findByEmail(userEmail);
    }

    @Test
    void shouldReadByIdSuccessfully() {
        // arrange
        int userId = 1;
        User expectedUser = new User();

        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

        // act
        User actualUser = userActions.readById(userId);

        // assert
        assertEquals(expectedUser, actualUser);
        verify(userRepository).findById(userId);
    }

    @Test
    void readByIdShouldThrowExceptionWhenUserNotFound() {
        // arrange
        int userId = 1;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // act & assert
        assertThrows(EntityNotFoundException.class, () -> userActions.readById(userId));
    }
}