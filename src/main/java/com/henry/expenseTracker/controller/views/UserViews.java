package com.henry.expenseTracker.controller.views;

import com.fasterxml.jackson.core.JsonProcessingException;
//import com.henry.expenseTracker.dao.dto.ExpenseResponseDto;
import com.henry.expenseTracker.Dto.request.UserRequestDto;
import com.henry.expenseTracker.Dto.response.UserResponseDto;
import com.henry.expenseTracker.service.impl.ExpenseService;
import com.henry.expenseTracker.service.impl.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Tag(name="User Templates")
@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserViews {
    private final ExpenseService expenseService;
    private final UserService userService;

    @GetMapping("/register")
    public String Register(Model model) {
        Map<String, List<?>> countriesInfo = this.userService.getCountries();
        model.addAttribute("countries", countriesInfo.get("countries"));
        model.addAttribute("currencies", countriesInfo.get("currencies"));
        return "registerUser";
    }
    @SneakyThrows
    @PostMapping("/register")
    public String RegisterSave(Model model,
                               @RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("country") String country,
                               @RequestParam("currrency") String currrency) {

        UserRequestDto userRequestDto = UserRequestDto.builder()
                .name(username)
                .email(email)
                .password(password)
                .country(country)
                .currency(currrency)
                .build();
        UserResponseDto user = userService.save(userRequestDto);
        if (user != null) {
            model.addAttribute("username", user.getName());
            return "redirect:/login";
        } else {
            model.addAttribute("username",username);
            model.addAttribute("email",email);
            model.addAttribute("password",password);
            model.addAttribute("errorRegister","Error creating data");
            return "registerUser";
        }

    }

    @SneakyThrows
    @GetMapping("/dashboard")
    public String dashboardPage(Model model,
                                @CookieValue(name = "username") String username,
                                @CookieValue(name = "userid") String userid
    ) throws JsonProcessingException {
        UserResponseDto user = userService.findById(Long.parseLong(userid));
        if (user.getId() == Long.parseLong(userid)) {
            model.addAttribute("username", user.getName());
//            List<ExpenseResponseDto> expenseList = expenseService.findAllRelationsByUser(user.get().getId());
//            System.out.println(expenseList);
            model.addAttribute("expenses", /*expenseList*/ new ArrayList<>());
            return "dashboard";
        } else {
            return "redirect:/";
        }

    }
}