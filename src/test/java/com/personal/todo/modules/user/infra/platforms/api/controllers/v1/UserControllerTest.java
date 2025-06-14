package com.personal.todo.modules.user.infra.platforms.api.controllers.v1;

import com.personal.todo.modules.auth.business.app.actions.ports.output.TokenGenerator;
import com.personal.todo.modules.task.business.app.actions.TaskActions;
import com.personal.todo.modules.task.business.entities.Task;
import com.personal.todo.modules.user.business.app.UserActions;
import com.personal.todo.modules.user.business.entities.Role;
import com.personal.todo.modules.user.business.entities.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private TokenGenerator tokenGenerator;
    @MockitoBean
    private UserDetailsService userDetailsService;
    @Autowired
    private UserController userController;
    @MockitoBean
    private UserActions userActions;
    @MockitoBean
    private TaskActions taskActions;

    @Test
    void shouldReadByIdSuccessfully() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();

        int userId = 1;

        User user = new User(
                "felix", "felix@gmail.com", "1234",
                new Role(Role.Values.USER, "description")
        );

        Mockito.when(userActions.readById(userId)).thenReturn(user);

        // act & assert
        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/" + userId)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Mockito.verify(userActions).readById(userId);
    }

    @Test
    void shouldReadAllTasksSuccessfully() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();

        int userId = 1;

        User user = new User(
                "felix", "felix@gmail.com", "1234",
                new Role(Role.Values.USER, "description")
        );

        List<Task> tasks = List.of(
                new Task("task 1", user),
                new Task("task 2", user)
        );

        Mockito.when(taskActions.readAllByUserId(userId)).thenReturn(tasks);

        // act & assert
        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/" + userId + "/tasks")
                        .header("Authorization", "token")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        Mockito.verify(taskActions).readAllByUserId(userId);
    }
}