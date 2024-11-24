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
    private final SupplierService supplierService = new SupplierService();
    private final ExpenseService expenseService = new ExpenseService();
    private final CategoryService categoryService = new CategoryService();
    private final UserService userService = new UserService();

    @GetMapping("/register")
    public String Register(Model model,@CookieValue(value="userid") String userid) {
        Optional<User> user = userService.findByPk(Integer.parseInt(userid));
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
    @PostMapping("/")
    public String RegisterSave(Model model,
                               @CookieValue(value="userid") String userid,
                               @RequestParam("description") String description,
                               @RequestParam("emit_date") String emit_date,
                               @RequestParam("supplier") String supplier,
                               @RequestParam("category") String category,
                               @RequestParam("expiration") String expiration,
                               @RequestParam("amount") String amount
                               ) {
        String result;
        Expense expense = new Expense();
        expense.setAmount(Double.parseDouble(amount));
        expense.setDescription(description);
        expense.setDate(Date.valueOf(emit_date));

        if(expense == null) {
            model.addAttribute("");
            model.addAttribute("errorValidation","Error in form. check input fields");
            result = "formExpense";
        } else {

            result = "redirect:/user/dashboard";
        }
        return result;
    }
}
