package dao.impl;
import dao.categoryExpensesDao;
import dao.dto.categoryExpensesDto;
import db.impl.jdbcConfigH2;
import exceptions.categoryNotFoundException;
import exceptions.expenseNotFoundException;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class categoryExpenseDaoImplH2 implements categoryExpensesDao {
    db.dbConnection dbConnection = new jdbcConfigH2();
    private final Connection connection;

    public categoryExpenseDaoImplH2(){
        this.connection = dbConnection.getConnection();
    }
    @Override
    public void add(categoryExpensesDto categoryExpensesDto) throws expenseNotFoundException, categoryNotFoundException {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO EXPENSE_CATEGORY(ID_CATEGORY, ID_EXPENSE) VALUES (?,?)");
            ps.setInt(1,categoryExpensesDto.getCategory());
            ps.setInt(2,categoryExpensesDto.getExpense());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            if (e instanceof JdbcSQLIntegrityConstraintViolationException) {
                if (e.getMessage().contains("ID_CATEGORY")) {
                    throw new categoryNotFoundException("category "+categoryExpensesDto.getCategory()+" not exists");
                } else if (e.getMessage().contains("ID_EXPENSE")) {
                    throw new expenseNotFoundException("expense "+categoryExpensesDto.getExpense()+" not exists");
                }
            } else System.out.println(String.valueOf(e));
        }
    }

    @Override
    public void delete(categoryExpensesDto categoryExpensesDto) throws expenseNotFoundException, categoryNotFoundException {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM EXPENSE_CATEGORY WHERE ID_CATEGORY = ? AND ID_EXPENSE = ?");
            ps.setInt(1,categoryExpensesDto.getCategory());
            ps.setInt(2,categoryExpensesDto.getExpense());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            if (e instanceof JdbcSQLIntegrityConstraintViolationException) {
                if (e.getMessage().contains("ID_CATEGORY")) {
                    throw new categoryNotFoundException("category "+categoryExpensesDto.getCategory()+" not exists");
                } else if (e.getMessage().contains("ID_EXPENSE")) {
                    throw new expenseNotFoundException("expense "+categoryExpensesDto.getExpense()+" not exists");
                }
            } else System.out.println(String.valueOf(e));
        }
    }
}
