package com.personal.todo.modules.task.business.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.personal.todo.TodoApplication;
import com.personal.todo.modules.task.business.types.TaskStatus;
import com.personal.todo.modules.user.business.entities.User;

@SpringBootTest(classes = TodoApplication.class)
@ActiveProfiles("test")
class TaskTest {
    @Test
    void taskShouldBeCompleted() {
        Task task = new Task("task 1", new User());
        task.complete();

        assertEquals(TaskStatus.COMPLETED, task.getStatus());
    }
}
