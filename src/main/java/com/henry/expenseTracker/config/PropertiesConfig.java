package com.henry.expenseTracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource(value = "configs/api_countries.properties"),
        @PropertySource(value = "configs/api_currency.properties"),
        @PropertySource(value = "configs/client_security.properties"),
        @PropertySource(value = "configs/redis.properties")
})

public class PropertiesConfig {
}
