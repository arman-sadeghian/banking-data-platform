package com.sadeghian.banking.bootstrap.config;

import com.sadeghian.banking.application.port.in.CreateTransactionUseCase;
import com.sadeghian.banking.application.usecase.CreateTransactionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public CreateTransactionUseCase createTransactionUseCase() {
        return new CreateTransactionService();
    }
}