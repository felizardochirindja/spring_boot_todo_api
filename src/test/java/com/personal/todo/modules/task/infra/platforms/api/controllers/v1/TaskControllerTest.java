package com.personal.todo.modules.task.infra.platforms.api.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.todo.modules.auth.business.app.actions.ports.output.TokenGenerator;
import com.personal.todo.modules.auth.infra.platforms.api.middlewares.TokenAuthFilter;
import com.personal.todo.modules.task.business.app.actions.TaskActions;
import com.personal.todo.modules.task.business.app.params.input.CreateTaskInput;
import com.personal.todo.modules.task.business.entities.Task;
import com.personal.todo.modules.task.business.types.TaskStatus;
import com.personal.todo.modules.task.infra.platforms.api.controllers.v1.requests.CreateTaskPayload;
import com.personal.todo.modules.user.business.entities.User;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@WebMvcTest(TaskController.class)
@ActiveProfiles("test")
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private TaskActions taskActions;
    @MockitoBean
    private TokenGenerator tokenGenerator;
    @MockitoBean
    private UserDetailsService userDetailsService;
    @Autowired
    private TokenAuthFilter tokenAuthFilter;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TaskController taskController;

    @Test
    void shouldCreateTaskSuccessfully() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(taskController)
                .addFilter(tokenAuthFilter)
                .build();

        // arrange
        CreateTaskPayload payload = new CreateTaskPayload("my task", 1);
        Task task = new Task(1, "my task", TaskStatus.PENDING, new User());

        String token = "Bearer fake_token";
        String email = "felix@gmail.com";

        Mockito.when(taskActions.create(Mockito.any(CreateTaskInput.class))).thenReturn(task);
        Mockito.when(tokenGenerator.validateToken(ArgumentMatchers.anyString())).thenReturn(email);

        UserDetails user = org.springframework.security.core.userdetails.User
                .withUsername(email)
                .password("12345")
                .authorities("ROLE_USER")
                .build();

        Mockito.when(userDetailsService.loadUserByUsername(email))
                .thenReturn(user);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/tasks")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("success"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("todo created successfully"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.task.title").value("my task"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.task.status").value(TaskStatus.PENDING.name()));
    }
}