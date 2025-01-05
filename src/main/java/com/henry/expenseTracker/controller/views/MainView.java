package com.henry.expenseTracker.controller.views;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.henry.expenseTracker.Dto.response.UserResponseDto;
import com.henry.expenseTracker.entity.User;
import com.henry.expenseTracker.service.impl.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Tag(name="Main templates")
@Controller
@AllArgsConstructor
@RequestMapping("/")
public class MainView {
    private final UserService userService;

    @GetMapping("/")
    public String mainPage(Model model) {
        return "main";
    }

    @GetMapping("/about")
    public String aboutPage(Model model) {

        return "about";
    }
    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String LoginRequestPage(Model model,
                                   @RequestParam("email") String email,
                                   @RequestParam("password") String password,
                                   HttpServletResponse response) throws JsonProcessingException {
        Optional<UserResponseDto> find = userService.login(email, password);
        if (find.isPresent()) {
            UserResponseDto user = find.get();
            final Cookie cookie = new Cookie("username", user.getName().split(" ")[0]);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(86400);
            response.addCookie(cookie);
            final Cookie cookieEmail = new Cookie("userid", String.valueOf(user.getId()));
            cookie.setHttpOnly(true);
            cookie.setMaxAge(86400);
            response.addCookie(cookieEmail);
            return "redirect:/user/dashboard";
        } else {
            model.addAttribute("errorlogin", "user or password incorrect");
            model.addAttribute("email", email);
            model.addAttribute("password", password);
            return "login";
        }
    }
}