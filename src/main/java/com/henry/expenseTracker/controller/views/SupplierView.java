package com.henry.expenseTracker.controller.views;

import com.henry.expenseTracker.Dto.request.SupplierRequestDto;
import com.henry.expenseTracker.Dto.response.UserResponseDto;
import com.henry.expenseTracker.service.impl.SupplierService;
import com.henry.expenseTracker.service.impl.UserService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/supplier")
public class SupplierView {
    private final UserService userService;
    private final SupplierService supplierService;

    public SupplierView(UserService userService, SupplierService supplierService) {
        this.supplierService = supplierService;
        this.userService = userService;
    }
    @SneakyThrows
    @GetMapping
    public String listAll(Model model, @CookieValue(value="userid") String userid){
        UserResponseDto user = userService.findById(Long.parseLong(userid));
        if (user.getId() == Long.parseLong(userid)) {
            model.addAttribute("supplierList",supplierService.findAll());
            return "suppliers";
        } else {
            return "redirect:/login";
        }
    }

    @SneakyThrows
    @PostMapping
    public String registerSave(Model model,
                               @CookieValue(value="userid") String userid,
                               @RequestParam String name
    ){
        UserResponseDto user = userService.findById(Long.parseLong(userid));
        if (user.getId() == Long.parseLong(userid)) {
            SupplierRequestDto supplier = new SupplierRequestDto(null,name);
            supplierService.save(supplier);
            return "redirect:/supplier";
        } else {
            return "redirect:/login";
        }
    }


    @SneakyThrows
    @GetMapping("/register")
    public String registerForm(Model model, @CookieValue(value="userid") String userid){
        UserResponseDto user = userService.findById(Long.parseLong(userid));
        if (user.getId() == Long.parseLong(userid)) {
            return "formSupplier";
        } else {
            return "redirect:/login";
        }
    }
}