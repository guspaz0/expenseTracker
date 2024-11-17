package entity;

import java.sql.Date;

public class Expiration {
    private int id;
    private int expenseId;
    private Date expiration;
    private double participation;

    @Override
    public String toString() {
        return "expenseExpiration{" +
                "id=" + id +
                ", expenseId=" + expenseId +
                ", expiration=" + expiration +
                ", participation=" + participation +
                '}';
    }

    public Expiration(){}

    public Expiration(int id, int expenseId, Date expiration, double participation) {
        this.id = id;
        this.expenseId = expenseId;
        this.expiration = expiration;
        this.participation = participation;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public double getParticipation() {
        return participation;
    }

    public void setParticipation(double participation) {
        this.participation = participation;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
