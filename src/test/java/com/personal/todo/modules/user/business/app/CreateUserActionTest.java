package com.personal.todo.modules.user.business.app;

import com.personal.todo.TodoApplication;
import com.personal.todo.modules.shared.exceptions.EntityNotFoundException;
import com.personal.todo.modules.user.adapters.repositories.RoleRepository;
import com.personal.todo.modules.user.adapters.repositories.UserRepository;
import com.personal.todo.modules.user.business.app.params.input.CreateUserInput;
import com.personal.todo.modules.user.business.entities.Role;
import com.personal.todo.modules.user.business.entities.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = TodoApplication.class)
@ActiveProfiles("test")
class CreateUserActionTest {
    @MockitoBean
    private UserRepository userRepository;
    @MockitoBean
    private RoleRepository roleRepository;
    @InjectMocks
    private CreateUserAction createUserAction;

    @Test
    void executeShouldCreateUserSuccessfully() {
        // arrange
        String userEmail = "felix@gmail.com";
        int roleId = 1;

        var params = mock(CreateUserInput.class);

        Role role = new Role(Role.Values.ADMIN, "administrator");
        User expectedUser = new User("felix", "felix@gmail.com", "1234", role);

        when(params.email()).thenReturn(userEmail);
        when(params.createUser(role)).thenReturn(expectedUser);
        when(params.role()).thenReturn(Role.Values.ADMIN);

        when(userRepository.findByEmail(userEmail)).thenReturn(Optional.empty());
        when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));
        when(userRepository.save(expectedUser)).thenReturn(expectedUser);

        // act
        User createdUser = createUserAction.execute(params);

        // assert
        assertEquals(expectedUser, createdUser);
        verify(userRepository).findByEmail(userEmail);
        verify(userRepository).save(expectedUser);
        verify(roleRepository).findById(roleId);
    }

    @Test
    void executeShouldThrowExceptionWhenUserAlreadyExists() {
        // arrange
        var params = mock(CreateUserInput.class);
        String userEmail = "felix@gmail.com";

        when(params.email()).thenReturn(userEmail);
        when(userRepository.findByEmail(userEmail)).thenReturn(Optional.of(new User()));

        // act & assert
        var exception = assertThrows(RuntimeException.class, () -> createUserAction.execute(params));
        assertEquals("user already exists!", exception.getMessage());
        verify(userRepository).findByEmail(userEmail);
    }

    @Test
    void executeShouldThrowExceptionWhenRoleDoesNotExist() {
        // arrange
        int roleId = 1;
        String userEmail = "felix@gmail.com";

        var createUserInput = mock(CreateUserInput.class);

        when(createUserInput.email()).thenReturn(userEmail);
        when(createUserInput.role()).thenReturn(Role.Values.ADMIN);
        when(userRepository.findByEmail(userEmail)).thenReturn(Optional.empty());
        when(roleRepository.findById(roleId)).thenReturn(Optional.empty());

        // act & assert
        var exception = assertThrows(EntityNotFoundException.class, () -> createUserAction.execute(createUserInput));
        assertEquals("role not found!", exception.getMessage());
        verify(userRepository).findByEmail(userEmail);
        verify(roleRepository).findById(roleId);
    }
}