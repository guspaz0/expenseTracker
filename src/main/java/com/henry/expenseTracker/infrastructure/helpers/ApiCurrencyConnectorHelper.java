package com.henry.expenseTracker.infrastructure.helpers;

import com.henry.expenseTracker.infrastructure.dtos.CurrencyExchangeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Currency;


@Slf4j
@Component
public class ApiCurrencyConnectorHelper {

    private final WebClient currencyWebClient;

    @Value(value="${api.key}")
    private String apiKey;

    @Autowired
    public ApiCurrencyConnectorHelper(@Qualifier(value="currencyApi") WebClient currencyWebClient) {
        this.currencyWebClient = currencyWebClient;
    }

    public CurrencyExchangeDto getExchangeRate(Currency base, Currency target) {;
            CurrencyExchangeDto exchangeDto = this.currencyWebClient
                    .get()
                    .uri(uri ->
                            uri.path("/v6/"+apiKey+"/pair/"+base+"/"+target)
                            .build())
                    .retrieve()
                    .bodyToMono(CurrencyExchangeDto.class)
                    .block();
            return exchangeDto;
    }

}
