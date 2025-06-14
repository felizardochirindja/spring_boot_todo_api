package com.personal.todo.modules.shared.configs;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi v1() {
        return GroupedOpenApi.builder()
                .group("v1")
                .packagesToScan(
//                        "com.personal.todoapp.modules.auth.infra.platforms.api.controllers.v1",
//                        "com.personal.todoapp.modules.tasks.infra.platforms.api.controllers.v1"
//                        "com.personal.todoapp.modules.users.infra.platforms.api.controllers.v1"
                )
                .addOpenApiCustomizer(openApi -> {
                    openApi.info(new Info()
                            .title("TodoApp API v1")
                            .description("Documentação da versão 1 da API")
                            .version("v1"));
                })
                .build();
    }
}
