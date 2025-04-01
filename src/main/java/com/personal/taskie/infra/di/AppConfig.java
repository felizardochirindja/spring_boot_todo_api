package com.personal.todo.infra.di;

import com.personal.todo.adapters.libs.JwtGeneratorAdapter;
import com.personal.todo.business.app.ports.output.TokenGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public TokenGenerator tokenGenerator() {
        return new JwtGeneratorAdapter();
    }
}
