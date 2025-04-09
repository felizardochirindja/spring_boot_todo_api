package com.personal.taskie.business.app.actions;

import com.personal.taskie.adapters.repos.TaskRepository;
import com.personal.taskie.adapters.repos.UserRepository;
import com.personal.taskie.business.app.exceptions.EntityNotFoundException;
import com.personal.taskie.business.app.params.CreateTaskParams;
import com.personal.taskie.business.entities.Task;
import com.personal.taskie.business.entities.User;
import com.personal.taskie.business.types.TodoStatus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

@SpringBootTest
class TaskActionsTest {
    @MockitoBean
    private UserRepository userRepository;
    @MockitoBean
    private TaskRepository taskRepository;
    @Autowired
    private TaskActions taskActions;

    @Test
    void shouldCreateTaskSuccessfully() {
        // Arrange
        int userId = 1;
        User user = new User("John", "john@gmail.com", "1234");
        Task expectedTask = new Task("Example Task", TodoStatus.PENDING, user);

        CreateTaskParams params = mock(CreateTaskParams.class);

        when(params.userId()).thenReturn(userId);
        when(params.createTodo(TodoStatus.PENDING, user)).thenReturn(expectedTask);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(taskRepository.save(expectedTask)).thenReturn(expectedTask);

        // Act
        Task createdTask = taskActions.create(params);

        // Assert
        assertEquals(expectedTask, createdTask);
        verify(userRepository).findById(userId);
        verify(taskRepository).save(expectedTask);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        // Arrange
        int userId = 1;
        CreateTaskParams params = mock(CreateTaskParams.class);

        when(params.userId()).thenReturn(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> taskActions.create(params));
        verify(userRepository).findById(userId);
    }
}