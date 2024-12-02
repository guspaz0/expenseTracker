package com.henry.expenseTracker.controller.views;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.henry.expenseTracker.entity.User;
import com.henry.expenseTracker.service.impl.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class MainController {
    private final UserService userService;

    public MainController(UserService userService) {
        this.userService=userService;
    }

    @GetMapping
    public String mainPage(Model model) {

        return "main";
    }
    @GetMapping("/error")
    public String errorPage(Model model) {

        return "error";
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
        Optional<User> find = userService.findAll().stream().filter(userdb ->
                    userdb.getEmail().equals(email) && userdb.getPassword().equals(password)
                ).findAny();
        if (find.isPresent()) {
            User user = find.get();
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
