package com.henry.expenseTracker.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info=@Info(
                title="Expense Tracker API",
                version = "1.0",
                description = "Documentation for endpoints in ExpenseTracker App"
        )
)
public class OpenApiConfig {

}
