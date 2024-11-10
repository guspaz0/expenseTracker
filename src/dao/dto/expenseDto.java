package dao.dto;


import java.sql.Date;

public class expenseDto {
    private String description;
    private Date date;
    private double amount;

    public expenseDto(){}

    public expenseDto(String description, Date date, Double amount){
        this.description=description;
        this.date=date;
        this.amount=amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
