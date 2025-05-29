package com.personal.todo.modules.task.business.app.actions;

import com.personal.todo.TodoApplication;
import com.personal.todo.modules.events.handlers.EventPublisher;
import com.personal.todo.modules.shared.exceptions.EntityNotFoundException;
import com.personal.todo.modules.task.adapters.repositories.TaskRepository;
import com.personal.todo.modules.task.business.app.params.input.CreateTaskInput;
import com.personal.todo.modules.task.business.app.params.input.UpdateTaskInput;
import com.personal.todo.modules.task.business.entities.Task;
import com.personal.todo.modules.task.business.types.TaskStatus;
import com.personal.todo.modules.task.events.TaskEventMessage;
import com.personal.todo.modules.task.events.TaskEventName;
import com.personal.todo.modules.user.adapters.repositories.UserRepository;
import com.personal.todo.modules.user.business.entities.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = TodoApplication.class)
@ActiveProfiles("test")
@TestPropertySource(properties = "app.topics.task_events=task_events")
class TaskActionsTest {
    @MockitoBean
    private UserRepository userRepository;
    @MockitoBean
    private TaskRepository taskRepository;
    @MockitoBean
    private EventPublisher eventPublisher;
    @Value("${app.topics.task_events}")
    private String taskEventsTopicName;
    @InjectMocks
    private TaskActions taskActions;

    @Test
    void shouldCreateTaskSuccessfully() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        int userId = 1;
        User user = new User();
        Task expectedTask = new Task("Example Task", user);

        CreateTaskInput params = mock(CreateTaskInput.class);

        when(params.userId()).thenReturn(userId);
        when(params.createTask(user)).thenReturn(expectedTask);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(taskRepository.save(expectedTask)).thenReturn(expectedTask);

        java.lang.reflect.Field taskEventsTopicField = TaskActions.class.getDeclaredField("taskEventsTopic");
        taskEventsTopicField.setAccessible(true);
        taskEventsTopicField.set(taskActions, taskEventsTopicName);

        TaskEventMessage event = TaskEventMessage.fromTodo(expectedTask, TaskEventName.TASK_CREATED);
        doNothing().when(eventPublisher).publish(taskEventsTopicName, event);

        // Act
        Task createdTask = taskActions.create(params);

        // Assert
        assertEquals(expectedTask, createdTask);
        verify(userRepository).findById(userId);
        verify(taskRepository).save(expectedTask);
        verify(eventPublisher).publish(eq(taskEventsTopicName), any(TaskEventMessage.class));
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