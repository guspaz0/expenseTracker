package entity;

import exceptions.ExpenseNotFoundException;

import java.util.List;

public class User {
    private String name;
    private String email;
    private List<Expense> expenses;

    public User(String name, String email, List<Expense> expenses) {
        this.name = name;
        this.email = email;
        this.expenses = expenses;
    }

    public void addExpense(Expense expense){
        this.expenses.add(expense);
    }
    public void removeExpense(Expense expense) {
        this.expenses.removeIf(expens -> expens.getId() == expense.getId());
    }
    public void updateExpense(Expense expense) throws ExpenseNotFoundException {
        try {
            for (Expense expens : this.expenses) {
                if (expens.getId() == expense.getId()) {
                    expens.setAmount(expense.getAmount());
                    expens.setDate(expense.getDate());
                    expens.setCategory(expense.getCategory());
                    expens.setDescription(expense.getDescription());
                    break;
                }
            }
            throw new ExpenseNotFoundException("No se encontro la expensa");
        } catch(ExpenseNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", expenses=" + expenses +
                '}';
    }
}
