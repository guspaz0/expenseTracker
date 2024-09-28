import entity.Expense;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseTrackerApp {
    public static void main(String[] args) {
        List<Expense> expenseList = new ArrayList<>();

        expenseList.add(new Expense(100, LocalDate.of(2024,10,24),0,));
    }
}
