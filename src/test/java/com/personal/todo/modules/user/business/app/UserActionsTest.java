package com.personal.todo.modules.user.business.app;

import com.personal.todo.TodoApplication;
import com.personal.todo.modules.shared.exceptions.EntityNotFoundException;
import com.personal.todo.modules.user.adapters.repositories.UserRepository;
import com.personal.todo.modules.user.business.entities.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TodoApplication.class)
@ActiveProfiles("test")
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