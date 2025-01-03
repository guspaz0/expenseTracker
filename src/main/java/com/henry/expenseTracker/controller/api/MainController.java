package com.henry.expenseTracker.controller.api;

import com.henry.expenseTracker.infrastructure.dtos.CurrencyExchangeDto;
import com.henry.expenseTracker.infrastructure.helpers.ApiCurrencyConnectorHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Currency;

@Slf4j
@Tag(name="Main")
@Controller
@RequestMapping("/")
public class MainController {
    private final ApiCurrencyConnectorHelper apiCurrency;

    public MainController(ApiCurrencyConnectorHelper apiCurrency){
        this.apiCurrency = apiCurrency;
    }

    @Operation(summary="Welcome message")
    @GetMapping("/api")
    public ResponseEntity<String> mainPage(){
        return ResponseEntity.ok("Expense Tracker API");
    }

    @Operation(summary="Default Thymeleaf Model error page")
    @GetMapping("/error")
    public String errorPage(Model model){
        return "error";
    }

    @Operation(summary = "Get exchange Rates")
    @RequestMapping(value = "/api/pair", method= RequestMethod.GET)
    public ResponseEntity<CurrencyExchangeDto> ExchangeRates(
            @RequestParam String base,
            @RequestParam String target) {
        log.info(String.valueOf(Currency.getInstance(base)));
        log.info(String.valueOf(Currency.getInstance(target)));
        CurrencyExchangeDto exchangeData = this.apiCurrency.getExchangeRate(Currency.getInstance(base),Currency.getInstance(target));
        return ResponseEntity.ok().body(exchangeData);
    }
}
