package com.personal.todo.modules.task.infra.adapters.libs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.reactive.function.client.WebClient;

import com.personal.todo.modules.task.business.app.ports.output.remotetask.RemoteTasksResponse;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

@SpringBootTest
@ActiveProfiles("test")
public class FetchRemoteTasksCircuitBreakerTest {
    @Autowired
    @Qualifier("dummyJsonTaskSyncFetcherByWebClient")
    private DummyJsonTaskSyncFetcherByWebClient fetcher;
    @MockitoBean
    private WebClient dummyJsonWebClient;
    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @BeforeEach
    void resetCircuitBreaker() {
        circuitBreakerRegistry
            .find("fetchTasksByUserId")
            .ifPresent(CircuitBreaker::reset);
    }

    @Test
    void shouldOpenCircuitAfterFailureThreshold() {
        WebClient.RequestHeadersUriSpec uriSpec = mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.RequestHeadersSpec headersSpec = mock(WebClient.RequestHeadersSpec.class);
        WebClient.ResponseSpec responseSpec = mock(WebClient.ResponseSpec.class);

        when(dummyJsonWebClient.get()).thenReturn(uriSpec);
        when(uriSpec.uri(anyString())).thenReturn(headersSpec);
        when(headersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(RemoteTasksResponse.class))
            .thenThrow(new RuntimeException("service unavailable"));

        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("fetchTasksByUserId");

        for (int i = 0; i < 4; i++) {
            fetcher.fetchTasksByUserId(1);
        }

        assertEquals(CircuitBreaker.State.OPEN, circuitBreaker.getState());
    }
}
