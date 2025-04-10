package com.personal.taskie.business.app.actions;

import com.personal.taskie.adapters.repos.TaskRepository;
import com.personal.taskie.adapters.repos.UserRepository;
import com.personal.taskie.business.app.exceptions.EntityNotFoundException;
import com.personal.taskie.business.app.params.CreateTaskParams;
import com.personal.taskie.business.app.params.UpdateTaskParams;
import com.personal.taskie.business.entities.Task;
import com.personal.taskie.business.entities.User;
import com.personal.taskie.business.types.TodoStatus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void createTaskShouldThrowExceptionWhenUserNotFound() {
        // Arrange
        int userId = 1;
        CreateTaskParams params = mock(CreateTaskParams.class);

        when(params.userId()).thenReturn(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> taskActions.create(params));
        verify(userRepository).findById(userId);
    }

    @Test
    void shouldUpdateTaskSuccessfully() {
        // arrange
        int taskId = 1;
        User user = new User("felix", "felix@gmail.com", "1234");
        Task expectedTask = new Task(taskId, "nova tarefa", TodoStatus.PENDING, user);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(expectedTask));
        when(taskRepository.save(expectedTask)).thenReturn(expectedTask);

        var params =  new UpdateTaskParams(taskId, "title", TodoStatus.PENDING);

        // act
        Task updatedTask = taskActions.update(params);

        // assert
        assertEquals(expectedTask, updatedTask);
        verify(taskRepository).findById(taskId);
        verify(taskRepository).save(expectedTask);
    }

    @Test
    void updateTaskShouldThrowExceptionWhenTaskNotFound() {
        // assert
        int taskId = 1;
        var params = mock(UpdateTaskParams.class);
        when(params.id()).thenReturn(taskId);
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // act & assert
        assertThrows(EntityNotFoundException.class, () -> taskActions.update(params));
        verify(taskRepository).findById(taskId);
    }
}