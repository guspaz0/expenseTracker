package com.henry.expenseTracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public ResponseEntity<String> mainPage(){
        return ResponseEntity.ok("Expense Tracker API");
    }

    @GetMapping("/error")
    public String errorPage(Model model){
        return "error";
    }
}
