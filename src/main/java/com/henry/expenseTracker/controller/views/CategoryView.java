package com.henry.expenseTracker.controller.views;

import com.henry.expenseTracker.Dto.request.CategoryRequestDto;
import com.henry.expenseTracker.Dto.response.CategoryResponseDto;
import com.henry.expenseTracker.Dto.response.UserResponseDto;
import com.henry.expenseTracker.service.impl.CategoryService;
import com.henry.expenseTracker.service.impl.UserService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryView {
    private final CategoryService categoryService;
    private final UserService userService;

    public CategoryView(CategoryService categoryService, UserService userService){
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @SneakyThrows
    @GetMapping
    public String listAll(Model model, @CookieValue(value="userid") String userid){
        UserResponseDto user = userService.findById(Long.parseLong(userid));
        if (user.getId() == Long.parseLong(userid)) {
            model.addAttribute("categoryList",categoryService.findAll());
            return "categories";
        } else {
            return "redirect:/login";
        }
    }

    @SneakyThrows
    @PostMapping
    public String registerSave(Model model,
                               @CookieValue(value="userid") String userid,
                               @RequestParam String name,
                               @RequestParam String description
    ){
        UserResponseDto user = userService.findById(Long.parseLong(userid));
        if (user.getId() == Long.parseLong(userid)) {
            CategoryRequestDto categoryRequestDto = new CategoryRequestDto(null,name,description);
            CategoryResponseDto category = categoryService.save(categoryRequestDto);
            if (Optional.ofNullable(category.getId()).isPresent()) {
                return "redirect:/category";
            } else {
                model.addAttribute("category",category);
                return "formCategory";
            }
        } else {
            return "redirect:/login";
        }
    }

    @SneakyThrows
    @GetMapping("/register")
    public String register(Model model, @CookieValue(value="userid") String userid){
        UserResponseDto user = userService.findById(Long.parseLong(userid));
        if (user.getId() == Long.parseLong(userid)) {
            return "formCategory";
        } else {
            return "redirect:/login";
        }
    }
}