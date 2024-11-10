package dao.impl;

import dao.dto.userExpensesDto;
import dao.userExpensesDao;
import db.dbConnection;
import db.impl.jdbcConfigH2;
import exceptions.expenseNotFoundException;
import exceptions.userNotFoundException;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class userExpensesDaoImplH2 implements userExpensesDao {
    db.dbConnection dbConnection = new jdbcConfigH2();
    private final Connection connection;

    public userExpensesDaoImplH2(){
        this.connection = dbConnection.getConnection();
    }

    @Override
    public void add(userExpensesDto userExpensesDto) throws expenseNotFoundException, userNotFoundException {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO USER_EXPENSES(ID_USER, ID_EXPENSE) VALUES (?,?)");
            ps.setInt(1,userExpensesDto.getUser());
            ps.setInt(2,userExpensesDto.getExpense());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            if (e instanceof JdbcSQLIntegrityConstraintViolationException) {
                if (e.getMessage().contains("ID_USER")) {
                    throw new userNotFoundException("user "+userExpensesDto.getUser()+" not exists");
                } else if (e.getMessage().contains("ID_EXPENSE")) {
                    throw new expenseNotFoundException("expense "+userExpensesDto.getExpense()+" not exists");
                }
            } else System.out.println(String.valueOf(e));
        }
    }

    @Override
    public void delete(userExpensesDto userExpensesDto) throws expenseNotFoundException, userNotFoundException {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM USER_EXPENSES WHERE ID_USER = ? AND ID_EXPENSE = ?");
            ps.setInt(1,userExpensesDto.getUser());
            ps.setInt(2,userExpensesDto.getExpense());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            if (e instanceof JdbcSQLIntegrityConstraintViolationException) {
                if (e.getMessage().contains("ID_USER")) {
                    throw new userNotFoundException("user "+userExpensesDto.getUser()+" not exists");
                } else if (e.getMessage().contains("ID_EXPENSE")) {
                    throw new expenseNotFoundException("expense "+userExpensesDto.getExpense()+" not exists");
                }
            } else System.out.println(String.valueOf(e));
        }
    }
}
