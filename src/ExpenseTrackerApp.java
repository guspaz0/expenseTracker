import entity.Category;
import entity.Expense;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseTrackerApp {
    public static void main(String[] args) {

        List<Category> categoryList = new ArrayList<>();

        categoryList.add(new Category("Servicios basicos", "Servicios de basicos por ejemplo agua, electricidad, gas, telefonia, etc."));
        categoryList.add(new Category("Bienes basicos", "Propductos basicos tangibles como por ejemplo ropa, comida"));

        List<Expense> expenseList = new ArrayList<>();

        expenseList.add(new Expense(100, LocalDate.of(2024,10,24),1,"factura de Energia periodo de agosto 2024"));

        categoryList.forEach(category -> System.out.println(category.toString()));
    }
}
