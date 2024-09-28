package entity;

import java.time.LocalDate;

public class Expense {
    private int id;
    private int amount;
    private String date;
    private int category;
    private String description;
    private static int lastIndex = 0;

    public Expense() {
        lastIndex++;
    }

    public Expense(int amount, LocalDate date, int category, String description) {
        this.id = lastIndex;
        this.amount = amount;
        this.date = String.valueOf(date);
        this.category = category;
        this.description = description;
    }

    public static int getLastIndex() {
        return lastIndex;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                '}';
    }
}
