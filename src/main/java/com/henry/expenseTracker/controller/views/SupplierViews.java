package com.henry.expenseTracker.controller.views;

import com.henry.expenseTracker.entity.Category;
import com.henry.expenseTracker.entity.Supplier;
import com.henry.expenseTracker.entity.User;
import com.henry.expenseTracker.service.impl.SupplierService;
import com.henry.expenseTracker.service.impl.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/supplier")
public class SupplierViews {
    private final UserService userService;
    private final SupplierService supplierService;

    public SupplierViews(UserService userService, SupplierService supplierService) {
        this.supplierService = supplierService;
        this.userService = userService;
    }

    @GetMapping
    public String listAll(Model model, @CookieValue(value="userid") String userid){
        Optional<User> user = userService.findById(Long.parseLong(userid));
        if (user.isPresent()) {
            model.addAttribute("supplierList",supplierService.findAll());
            return "suppliers";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping
    public String registerSave(Model model,
                               @CookieValue(value="userid") String userid,
                               @RequestParam String name
                               ){
        Optional<User> user = userService.findById(Long.parseLong(userid));
        if (user.isPresent()) {
            Supplier supplier = new Supplier();
            supplier.setName(name);
            supplierService.save(supplier);
            return "redirect:/supplier";
        } else {
            return "redirect:/login";
        }
    }



    @GetMapping("/register")
    public String registerForm(Model model, @CookieValue(value="userid") String userid){
        Optional<User> user = userService.findById(Long.parseLong(userid));
        if (user.isPresent()) {
            return "formSupplier";
        } else {
            return "redirect:/login";
        }
    }
}
