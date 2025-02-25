package com.henry.expenseTracker.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Configuration
public class WebClientConfig {

    //aqui se definen las variables de archivos properties definidas en /resources/configs
    //por ejemplo:

    @Value(value="${api.base-urlCountries}")
    private String baseUrlCountries;

    @Value(value="${api.base-urlCurrency}")
    private String baseUrlCurrency;

    @Value(value="${api.query1}")
    private String query1;

    @Value(value="${api.query2}")
    private String query2;

    @Value(value="${api.key}")
    private String apikey;



    @Bean(name="countriesApi")
    public WebClient countriesWebClient(){
        return WebClient.builder()
                .baseUrl(baseUrlCountries+"?codes={whitelist}&fields={fields}")
                .defaultUriVariables(Map.of("whitelist",query1, "fields", query2))
                .build();
    }

    @Bean(name="currencyApi")
    public WebClient currencyWebClient(){
        return WebClient.builder()
                .baseUrl(baseUrlCurrency)
                .build();
    }
}
