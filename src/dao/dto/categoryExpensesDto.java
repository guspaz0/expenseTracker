package dao.dto;

public class categoryExpensesDto {
    private int category;
    private int expense;

    public categoryExpensesDto(int category, int expense){
        this.category=category;
        this.expense=expense;
    }
    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }
}
