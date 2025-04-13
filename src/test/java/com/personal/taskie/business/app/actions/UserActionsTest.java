package com.personal.taskie.business.app.actions;

import com.personal.taskie.adapters.repos.UserRepository;
import com.personal.taskie.business.entities.exceptions.EntityNotFoundException;
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
    @MockitoBean
    private CreateUserAction createUserAction;
    @InjectMocks
    private UserActions userActions;

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