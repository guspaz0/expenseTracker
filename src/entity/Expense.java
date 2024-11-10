package entity;

import java.sql.Date;

public class Expense {
    private int id;
    private double amount;
    private Date date;
    private int category;
    private String description;

    public Expense() {}

    public Expense(double amount, Date date, int category, String description) {
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.description = description;
    }

    public void setId(int id) {this.id = id;}

    public int getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
