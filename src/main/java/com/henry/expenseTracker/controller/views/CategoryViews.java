package com.henry.expenseTracker.controller.views;

import com.henry.expenseTracker.entity.Category;
import com.henry.expenseTracker.entity.User;
import com.henry.expenseTracker.service.impl.CategoryService;
import com.henry.expenseTracker.service.impl.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryViews {
    private final CategoryService categoryService;
    private final UserService userService;

    public CategoryViews(CategoryService categoryService, UserService userService){
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping
    public String listAll(Model model, @CookieValue(value="userid") String userid){
        Optional<User> user = userService.findById(Long.parseLong(userid));
        if (user.isPresent()) {
            model.addAttribute("categoryList",categoryService.findAll());
            return "categories";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping
    public String registerSave(Model model,
                               @CookieValue(value="userid") String userid,
                               @RequestParam String name,
                               @RequestParam String description
                               ){
        Optional<User> user = userService.findById(Long.parseLong(userid));
        if (user.isPresent()) {
            Category category = categoryService.save(new Category(name, description));
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

    @GetMapping("/register")
    public String register(Model model, @CookieValue(value="userid") String userid){
        Optional<User> user = userService.findById(Long.parseLong(userid));
        if (user.isPresent()) {
            return "formCategory";
        } else {
            return "redirect:/login";
        }
    }


}
