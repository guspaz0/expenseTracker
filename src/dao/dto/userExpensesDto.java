package dao.dto;

public class userExpensesDto {
    private int user;
    private int expense;

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getExpense() {
        return expense;
    }

    public void setExpense(int category) {
        this.expense = expense;
    }

    public userExpensesDto(int user, int expense) {
        this.user=user;
        this.expense=expense;
    }
}
