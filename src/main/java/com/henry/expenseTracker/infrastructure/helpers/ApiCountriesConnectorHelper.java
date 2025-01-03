package com.henry.expenseTracker.infrastructure.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.henry.expenseTracker.infrastructure.dtos.CountryDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ApiCountriesConnectorHelper {
    private final WebClient countriesWebClient;

    public ApiCountriesConnectorHelper(@Qualifier(value="countries") WebClient countriesWebClient) {
        this.countriesWebClient = countriesWebClient;
    }

    public Map<String, List<?>> getCountries() {
        Map<String, List<?>>  response = new HashMap<>();
        CountryDTO[] List = this.countriesWebClient
                .get()
                .retrieve()
                .bodyToMono(CountryDTO[].class)
                .block();
        assert List != null;
        List<Currency> currencies = new ArrayList<Currency>();
        Arrays.stream(List)
            .forEach(country ->
                country.getCurrencies()
                    .keySet()
                    .stream()
                    .map(currency -> Currency.getInstance(currency.getCurrencyCode()))
                    .forEach(currencies::add)
            );
        response.put("countries", Arrays.stream(List).toList());
        response.put("currencies", currencies);
        return response;
    }
}
