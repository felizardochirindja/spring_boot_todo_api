package com.personal.taskie.business.app.actions;

import com.personal.taskie.adapters.repos.UserRepository;
import com.personal.taskie.business.app.params.input.CreateUserInput;
import com.personal.taskie.business.entities.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CreateUserActionTest {
    @MockitoBean
    private UserRepository userRepository;
    @InjectMocks
    private CreateUserAction createUserAction;

    @Test
    void executeShouldCreateUserSuccessfully() {
        // arrange
        String userEmail = "felix@gmail.com";

        var params = mock(CreateUserInput.class);
        User expectedUser = new User("felix", "felix@gmail.com", "1234");

        when(params.email()).thenReturn(userEmail);
        when(params.createUser()).thenReturn(expectedUser);

        when(userRepository.findByEmail(userEmail)).thenReturn(Optional.empty());
        when(userRepository.save(expectedUser)).thenReturn(expectedUser);

        // act
        User createdUser = createUserAction.execute(params);

        // assert
        assertEquals(expectedUser, createdUser);
        verify(userRepository).findByEmail(userEmail);
        verify(userRepository).save(expectedUser);
    }

    @Test
    void executeShouldThrowExceptionWhenUserAlreadyExists() {
        // arrange
        var params = mock(CreateUserInput.class);
        String userEmail = "felix@gmail.com";

        when(params.email()).thenReturn(userEmail);
        when(userRepository.findByEmail(userEmail)).thenReturn(Optional.of(new User()));

        // act & assert
        assertThrows(RuntimeException.class, () -> createUserAction.execute(params));
        verify(userRepository).findByEmail(userEmail);
    }
}