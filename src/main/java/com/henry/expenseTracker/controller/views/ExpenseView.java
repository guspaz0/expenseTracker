package com.henry.expenseTracker.controller.views;

import com.henry.expenseTracker.Dto.request.ExpenseRequestDto;
import com.henry.expenseTracker.Dto.response.CategoryResponseDto;
import com.henry.expenseTracker.Dto.response.ExpenseResponseDto;
import com.henry.expenseTracker.Dto.response.SupplierResponseDto;
import com.henry.expenseTracker.Dto.response.UserResponseDto;
import com.henry.expenseTracker.service.impl.CategoryService;
import com.henry.expenseTracker.service.impl.ExpenseService;
import com.henry.expenseTracker.service.impl.SupplierService;
import com.henry.expenseTracker.service.impl.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name="Expense templates")
@Controller
@AllArgsConstructor
@RequestMapping("/expense")
public class ExpenseView {
    private final ExpenseService expenseService;
    private final SupplierService supplierService;
    private final UserService userService;
    private final CategoryService categoryService;

    @SneakyThrows
    @PostMapping
    public String RegisterSave(Model model,
                               @CookieValue(value="userid") String userid,
                               @RequestParam("description") String description,
                               @RequestParam("emit_date") String emit_date,
                               @RequestParam("supplier") String supplier,
                               @RequestParam("category") String category,
                               @RequestParam(value = "expires", defaultValue = "0") String expires,
                               @RequestParam("amount") String amount
    ) {
        UserResponseDto user = userService.findById(Long.parseLong(userid));
        if (user.getId() == Long.parseLong(userid)) {
            String result = null;
            new ExpenseRequestDto();
            ExpenseRequestDto expenseRequestDto = ExpenseRequestDto.builder()
                    .amount(Double.parseDouble(amount))
                    .description(description)
                    .emitDate(LocalDate.parse(emit_date))
                    .supplier(Long.parseLong(supplier))
                    .category(Long.parseLong(category))
                    .userId(Long.parseLong(userid))
                    .expires(Integer.parseInt(expires))
                    .build();

            ExpenseResponseDto response = expenseService.save(expenseRequestDto);

            if(response == null) {
                model.addAttribute("supplierList", supplierService.findAll());
                model.addAttribute("categoryList", categoryService.findAll());
                model.addAttribute("expenseRequestDto", expenseRequestDto);
                model.addAttribute("errorValidation","Error in form. check input fields");
                result = "formExpense";
            } else {
                result = "redirect:/user/dashboard";
            }
            return result;
        } else {
            return "redirect:/login";
        }
    }
    @SneakyThrows
    @GetMapping("/register")
    public String Register(Model model,@CookieValue(value="userid") String userid) {
        UserResponseDto user = userService.findById(Long.parseLong(userid));
        if (user.getId() == Long.parseLong(userid)) {
            List<SupplierResponseDto> supplierList = supplierService.findAll();
            List<CategoryResponseDto> categoryList = categoryService.findAll();
            model.addAttribute("supplierList",supplierList);
            model.addAttribute("categoryList", categoryList);
            return "formExpense";
        } else {
            return "redirect:/login";
        }
    }

}