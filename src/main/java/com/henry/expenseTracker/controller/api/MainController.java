package com.henry.expenseTracker.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name="Main")
@Controller
@RequestMapping("/")
public class MainController {

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
}
