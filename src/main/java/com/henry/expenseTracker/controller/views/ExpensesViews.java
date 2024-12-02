package com.henry.expenseTracker.controller.views;

import com.henry.expenseTracker.entity.Category;
import com.henry.expenseTracker.entity.Expense;
import com.henry.expenseTracker.entity.Supplier;
import com.henry.expenseTracker.entity.User;
import com.henry.expenseTracker.service.impl.CategoryService;
import com.henry.expenseTracker.service.impl.ExpenseService;
import com.henry.expenseTracker.service.impl.SupplierService;
import com.henry.expenseTracker.service.impl.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/expense")
public class ExpensesViews {
    private final SupplierService supplierService;
    private final ExpenseService expenseService;
    private final CategoryService categoryService;
    private final UserService userService;

    public ExpensesViews(SupplierService supplierService, ExpenseService expenseService, CategoryService categoryService, UserService userService) {
        this.supplierService = supplierService;
        this.expenseService = expenseService;
        this.categoryService = categoryService;
        this.userService = userService;
    }


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
        Optional<User> user = userService.findById(Long.parseLong(userid));
        if (user.isPresent()) {
            String result = null;
//            Expense expense = new Expense();
//            expense.setAmount(Double.parseDouble(amount));
//            expense.setDescription(description);
//            expense.setEmit_date(Date.valueOf(emit_date));
//            expense.setSupplier_id(Integer.parseInt(supplier));
//            expense.setCategory_id(Integer.parseInt(category));
//            expense.setUser_id(Integer.parseInt(userid));
//            expense.setExpires(Integer.parseInt(expires));
//            System.out.println(expense.toString());
            //Expense response = expenseService.save(expense);

//            if(response == null) {
//                model.addAttribute("supplierList", supplierService.findAll());
//                model.addAttribute("categoryList", categoryService.findAll());
//                model.addAttribute("expenseRequestDto", expense);
//                model.addAttribute("errorValidation","Error in form. check input fields");
//                result = "formExpense";
//            } else {
                result = "redirect:/user/dashboard";
//            }
            return result;
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/register")
    public String Register(Model model,@CookieValue(value="userid") String userid) {
        Optional<User> user = userService.findById(Long.parseLong(userid));
        if (user.isPresent()) {
            List<Supplier> supplierList = supplierService.findAll();
            List<Category> categoryList = categoryService.findAll();
            model.addAttribute("supplierList",supplierList);
            model.addAttribute("categoryList", categoryList);
            return "formExpense";
        } else {
            return "redirect:/login";
        }
    }

}
