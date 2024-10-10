import entity.Category;
import entity.Expense;
import entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseTrackerApp {
    public static void main(String[] args) {

        List<Category> categoryList = new ArrayList<>();

        categoryList.add(new Category("Servicios basicos", "Servicios de basicos por ejemplo agua, electricidad, gas, telefonia, etc."));
        categoryList.add(new Category("Bienes basicos", "Propductos basicos tangibles como por ejemplo ropa, comida"));


        List<Expense> expenseList = new ArrayList<>();

        expenseList.add(new Expense(100, LocalDate.of(2024,10,24),1,"factura de Electricidad periodo de agosto 2024"));
        expenseList.add(new Expense(150, LocalDate.of(2024,9,28),1,"factura de Gas periodo de agosto 2024"));
        expenseList.add(new Expense(300, LocalDate.of(2024,8,3),1,"factura de Telefonia movil periodo de agosto 2024"));
        expenseList.add(new Expense(1150, LocalDate.of(2024,9,15),2,"factura de comida"));
        expenseList.add(new Expense(2300, LocalDate.of(2024,8,26),2,"factura de Ropa"));
        expenseList.add(new Expense(5000, LocalDate.of(2024,10,12),2,"factura de Universidad"));

        User user = new User("Gustavo", "gusti.paz@gmail.com", expenseList);

        categoryList.forEach(category -> System.out.println(category.toString()));


    }
}
