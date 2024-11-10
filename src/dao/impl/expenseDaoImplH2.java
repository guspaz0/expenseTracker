package dao.impl;

import dao.dto.expenseDto;
import dao.expenseDao;
import db.dbConnection;
import db.impl.jdbcConfigH2;
import entity.Category;
import entity.Expense;
import exceptions.categoryNotFoundException;
import exceptions.categoryRepeatedException;
import exceptions.expenseNotFoundException;
import exceptions.expenseRepeatedException;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class expenseDaoImplH2 implements expenseDao {
    dbConnection dbConnection = new jdbcConfigH2();
    private final Connection connection;

    public expenseDaoImplH2(){
        this.connection = dbConnection.getConnection();
    }

    @Override
    public List<Expense> findAll() {
        try {
            List<Expense> expenseList = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM EXPENSES");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Expense expense = new Expense();
                expense.setId(rs.getInt("ID"));
                expense.setAmount(rs.getDouble("AMOUNT"));
                expense.setDate(rs.getDate("DATE"));
                expense.setDescription(rs.getString("DESCRIPTION"));
                expenseList.add(expense);
            }
            rs.close();
            ps.close();
            return expenseList;
        } catch (Exception e) {
            System.out.println(String.valueOf(e));
            return null;
        }
    }

    @Override
    public Expense findByPk(int id) throws expenseNotFoundException {
        try {
            Expense expense = new Expense();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM EXPENSES WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                expense.setId(rs.getInt("ID"));
                expense.setDate(rs.getDate("DATE"));
                expense.setDescription(rs.getString("DESCRIPTION"));
                expense.setAmount(rs.getDouble("AMOUNT"));
                rs.close();
                ps.close();
                return expense;
            } else {
                throw new expenseNotFoundException("expense "+ id + "not found");
            }
        } catch (Exception e) {
            if (e instanceof expenseNotFoundException) {
                throw new expenseNotFoundException(e.getMessage());
            } else System.out.println(String.valueOf(e));;
            return null;
        }
    }

    @Override
    public void create(expenseDto expenseDto) throws expenseRepeatedException {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO EXPENSES (AMOUNT, DATE, DESCRIPTION) VALUES (?, ?, ?)");
            ps.setDouble(1,expenseDto.getAmount());
            ps.setDate(2, expenseDto.getDate());
            ps.setString(3,expenseDto.getDescription());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e){
            System.out.println(String.valueOf(e));
        }
    }

    @Override
    public void update(Expense expense) throws expenseRepeatedException {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE EXPENSES SET AMOUNT=?, DATE=?, DESCRIPTION=? WHERE ID=?");
            ps.setDouble(1,expense.getAmount());
            ps.setDate(2, expense.getDate());
            ps.setString(3,expense.getDescription());
            ps.setInt(4,expense.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e){
            System.out.println(String.valueOf(e));
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM EXPENSES WHERE ID = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(String.valueOf(e));
        }
    }
}
