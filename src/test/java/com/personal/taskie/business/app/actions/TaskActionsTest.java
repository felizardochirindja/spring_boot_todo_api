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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
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
        Task expectedTask = new Task("Example Task", user);

        CreateTaskParams params = mock(CreateTaskParams.class);

        when(params.userId()).thenReturn(userId);
        when(params.createTask(user)).thenReturn(expectedTask);

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
        String expectedTaskTitle = "tarefa actualizada";

        User user = new User();
        Task existingTask = new Task(taskId, "tarefa existente", TodoStatus.PENDING, user);
        Task expectedTask = new Task(taskId, expectedTaskTitle, TodoStatus.COMPLETED, user);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenReturn(expectedTask);

        // act
        var params =  new UpdateTaskParams(taskId, expectedTaskTitle, TodoStatus.COMPLETED);
        Task updatedTask = taskActions.update(params);

        // assert
        assertEquals(expectedTask, updatedTask);
        verify(taskRepository).findById(taskId);
        verify(taskRepository).save(any(Task.class));
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

    @Test
    void readAllByUserIdShouldThrowExceptionWhenUserNotFound() {
        // arrange
        int userId = 1;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // act & assert
        assertThrows(EntityNotFoundException.class, () -> taskActions.readAllByUserId(userId));
        verify(userRepository).findById(userId);
    }

    @Test
    void shouldRealAllByUserIdSuccessfully() {
        // arrange
        int userId = 1;

        var expectedTasks = List.of(
                new Task(),
                new Task()
        );

        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
        when(taskRepository.findAllByUserId(userId)).thenReturn(expectedTasks);
        
        // act
        var actualTasks = taskActions.readAllByUserId(userId);

        // assert
        assertNotNull(expectedTasks);
        assertEquals(expectedTasks, actualTasks);
        verify(userRepository).findById(userId);
        verify(taskRepository).findAllByUserId(userId);
    }

    @Test
    void readByIdShouldThrowExceptionWhenUserIdIsLessThanOne()
    {
        // arrange
        int taskId = 0;

        // act & assert
        assertThrows(RuntimeException.class, () -> taskActions.readById(taskId));
    }

    @Test
    void readByIdShoudThrowExceptionWenTaskNotFound() {
        int taskId = 1;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> taskActions.readById(taskId));
        verify(taskRepository).findById(taskId);
    }
}