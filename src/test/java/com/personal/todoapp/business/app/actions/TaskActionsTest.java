package com.personal.todoapp.business.app.actions;

import com.personal.todoapp.modules.task.adapters.repositories.TaskRepository;
import com.personal.todoapp.modules.user.adapters.repositories.UserRepository;
import com.personal.todoapp.modules.shared.exceptions.EntityNotFoundException;
import com.personal.todoapp.modules.task.business.app.params.input.CreateTaskInput;
import com.personal.todoapp.modules.task.business.app.params.input.UpdateTaskInput;
import com.personal.todoapp.modules.task.business.entities.Task;
import com.personal.todoapp.modules.task.business.app.actions.TaskActions;
import com.personal.todoapp.modules.user.business.entities.User;
import com.personal.todoapp.modules.task.business.types.TaskStatus;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @InjectMocks
    private TaskActions taskActions;

    @Test
    void shouldCreateTaskSuccessfully() {
        // Arrange
        int userId = 1;
        User user = new User();
        Task expectedTask = new Task("Example Task", user);

        CreateTaskInput params = mock(CreateTaskInput.class);

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
        CreateTaskInput params = mock(CreateTaskInput.class);

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

        User user = new User();
        Task existingTask = new Task(taskId, "tarefa existente", TaskStatus.PENDING, user);
        Task expectedTask = new Task(taskId, "tarefa actualizada", TaskStatus.COMPLETED, user);

        var params = mock(UpdateTaskInput.class);

        when(params.id()).thenReturn(taskId);
        when(params.createTask(user)).thenReturn(expectedTask);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(expectedTask)).thenReturn(expectedTask);

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
        var params = mock(UpdateTaskInput.class);
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
    void readByIdShouldThrowExceptionWenTaskNotFound() {
        int taskId = 1;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> taskActions.readById(taskId));
        verify(taskRepository).findById(taskId);
    }

    void shoudReadTaskByIdSuccessfully() {

    }
}