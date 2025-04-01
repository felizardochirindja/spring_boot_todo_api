package com.personal.taskie.infra.di;

import com.personal.taskie.adapters.libs.JwtGeneratorAdapter;
import com.personal.taskie.business.app.ports.output.TokenGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public TokenGenerator tokenGenerator() {
        return new JwtGeneratorAdapter();
    }
}
