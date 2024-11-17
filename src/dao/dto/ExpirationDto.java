package dao.dto;

import java.sql.Date;

public class ExpirationDto {
    private int expenseId;
    private Date expiration;
    private double participation;

    public ExpirationDto(){}

    public ExpirationDto(int expenseId, Date expiration, double participation) {
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

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public double getParticipation() {
        return participation;
    }

    public void setParticipation(double participation) {
        this.participation = participation;
    }

}
