package com.henry.expenseTracker.controller.views;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.henry.expenseTracker.dao.dto.ExpenseRelationsDto;
import com.henry.expenseTracker.entity.Expense;
import com.henry.expenseTracker.entity.User;
import com.henry.expenseTracker.service.impl.ExpenseService;
import com.henry.expenseTracker.service.impl.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/user")
public class UserViews {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ExpenseService expenseService = new ExpenseService();
    private final UserService userService = new UserService();

    @GetMapping("/register")
    public String Register(Model model) {
        return "registerUser";
    }
    @PostMapping("/register")
    public String RegisterSave(Model model,
                               @RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password) {
        UserService userService = new UserService();
        User user = userService.save(new User(username, email, password));
        if (user != null) {
            model.addAttribute("username", user.getName());
            return "redirect:/user/dashboard";
        } else {
            model.addAttribute("username",username);
            model.addAttribute("email",email);
            model.addAttribute("password",password);
            model.addAttribute("errorRegister","Error creating data");
            return "registerUser";
        }

    }
    @GetMapping("/dashboard")
    public String dashboardPage(Model model,
                                @CookieValue(name = "username") String username,
                                @CookieValue(name = "userid") String userid
    ) throws JsonProcessingException {
        Optional<User> user = userService.findByPk(Integer.parseInt(userid));
        if (user.isPresent()) {
            model.addAttribute("username", user.get().getName());
            List<ExpenseRelationsDto> expenseList = expenseService.findAllRelationsByUser(user.get().getId());
            System.out.println(expenseList);
            model.addAttribute("expenses", expenseList);
            return "dashboard";
        } else {
            return "redirect:/";
        }

    }
}
