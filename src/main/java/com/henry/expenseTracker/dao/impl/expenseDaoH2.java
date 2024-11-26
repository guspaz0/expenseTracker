package com.henry.expenseTracker.dao.impl;

import com.henry.expenseTracker.controller.views.Dto.ExpenseRequestDto;
import com.henry.expenseTracker.controller.views.Dto.ExpenseRequestUpdateDto;
import com.henry.expenseTracker.dao.IDao;
import com.henry.expenseTracker.dao.dto.ExpenseResponseDto;
import com.henry.expenseTracker.dao.expenseIDao;
import com.henry.expenseTracker.db.dbConnection;
import com.henry.expenseTracker.db.impl.jdbcConfigH2;
import com.henry.expenseTracker.entity.Category;
import com.henry.expenseTracker.entity.Expense;
import com.henry.expenseTracker.entity.Expiration;
import com.henry.expenseTracker.entity.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class expenseDaoH2 implements expenseIDao, IDao<Expense> {
    private dbConnection dbConnection;

    public expenseDaoH2(){
        this.dbConnection = new jdbcConfigH2();
    }
    public expenseDaoH2(dbConnection dbConnection){

        this.dbConnection = dbConnection;
    }

    private final static String FIND_ALL = "SELECT * FROM EXPENSES";
    private final static String FIND_BY_PK = "SELECT * FROM EXPENSES WHERE ID = ?";
    private final static String CREATE = "INSERT INTO EXPENSES (DESCRIPTION, EMIT_DATE, AMOUNT, ID_USER, ID_SUPPLIER, ID_CATEGORY, EXPIRES) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')";
    private final static String UPDATE = "UPDATE EXPENSES SET DATE = '%s', DESCRIPTION = '%s', ID_CATEGORY = '%' WHERE ID = '%s'";
    private final static String DELETE = "DELETE FROM EXPENSES WHERE ID = '%s'";
    private final static String FIND_BY_PK_ALL_RELATIONS = "SELECT e.id AS ID, e.EMIT_DATE AS EMIT_DATE, e.DESCRIPTION AS DESCRIPTION, e.AMOUNT AS AMOUNT, "+
            "CASE "+
            "WHEN e.expires = 1 THEN ARRAY_AGG(DISTINCT ARRAY[e2.id::TEXT,e2.EXPIRATION::TEXT, e2.PARTICIPATION::TEXT]) "+
            "ELSE null "+
            "END AS EXPIRATIONS, "+
            "ARRAY[s.ID::text,s.NAME] AS SUPPLIER, "+
            "ARRAY[c.id::text, c.name] AS CATEGORY "+
            "FROM EXPENSES E "+
            "LEFT JOIN CATEGORY C ON C.ID = E.ID_CATEGORY "+
            "LEFT JOIN SUPPLIERS s ON s.ID = e.ID_SUPPLIER "+
            "LEFT JOIN EXPIRATIONS e2 ON e2.ID_EXPENSE = e.id "+
            "WHERE e.ID = ? "+
            "GROUP BY e.id,e2.id_expense";
    private final static String FIND_ALL_RELATIONS_BY_USER = "SELECT e.id AS ID, e.EMIT_DATE AS EMIT_DATE, e.DESCRIPTION AS DESCRIPTION, e.AMOUNT AS AMOUNT, "+
    "CASE "+
    "WHEN e.expires = 1 THEN ARRAY_AGG(DISTINCT ARRAY[e2.id::TEXT,e2.EXPIRATION::TEXT, e2.PARTICIPATION::TEXT]) "+
    "ELSE null "+
    "END AS EXPIRATIONS, "+
    "ARRAY[s.ID::text,s.NAME] AS SUPPLIER, "+
    "ARRAY[c.id::text, c.name] AS CATEGORY "+
    "FROM EXPENSES E "+
    "LEFT JOIN CATEGORY C ON C.ID = E.ID_CATEGORY "+
    "LEFT JOIN SUPPLIERS s ON s.ID = e.ID_SUPPLIER "+
    "LEFT JOIN EXPIRATIONS e2 ON e2.ID_EXPENSE = e.id "+
    "WHERE e.ID_USER = ? "+
    "GROUP BY e.id,e2.id_expense";


    @Override
    public List<Expense> findAll() {
        Connection connection = dbConnection.getConnection();
        List<Expense> expenseList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                expenseList.add(createExpenseObject(rs));
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expenseList;
    }

    @Override
    public Optional<Expense> findByPk(int id) {
        Connection connection = dbConnection.getConnection();
        Expense expense = null;
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_BY_PK);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                expense = createExpenseObject(rs);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expense != null? Optional.of(expense) : Optional.empty();
    }

    @Override
    public Expense save(Expense expense) {
        Connection connection = dbConnection.getConnection();
        Optional<Expense> response = null;
        Statement stmt = null;
        String query = String.format(CREATE,
                expense.getDescription(),
                expense.getEmit_date(),
                expense.getAmount(),
                expense.getUser_id(),
                expense.getSupplier_id(),
                expense.getCategory_id(),
                expense.getExpires()
                );
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                int id = keys.getInt(1);
                response = this.findByPk(id);
            }
            keys.close();
            stmt.close();
            connection.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return response.get();
    }

    @Override
    public Expense update(Expense expense) {
        Connection connection = dbConnection.getConnection();
        String query = String.format(UPDATE,
                expense.getEmit_date(),
                expense.getDescription(),
                expense.getCategory_id(),
                expense.getId());
        execute(connection,query);
        return expense;
    }

    @Override
    public void delete(int id) {
        Connection connection = dbConnection.getConnection();
        String query = String.format(DELETE, id);
        execute(connection,query);
    }

    @Override
    public Optional<ExpenseResponseDto> findAllRelationsByPk(int id) {
        Connection connection = dbConnection.getConnection();
        ExpenseResponseDto expense = null;
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_BY_PK_ALL_RELATIONS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                expense = createExpenseRelationsObject(rs);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expense != null? Optional.of(expense) : Optional.empty();
    }

    @Override
    public List<ExpenseResponseDto> findAllRelationsByUser(int id) {
        Connection connection = dbConnection.getConnection();
        List<ExpenseResponseDto> expenseList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_ALL_RELATIONS_BY_USER);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ExpenseResponseDto expense = createExpenseRelationsObject(rs);
                expenseList.add(expense);
            }
            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return expenseList;
    }


    @Override
    public void addExpiration(Expiration expiration) {

    }

    @Override
    public void removeExpiration(Expiration expiration) {

    }

    @Override
    public void removeExpiration(int id_expiration) {

    }

    private Expense createExpenseObject(ResultSet rs) throws SQLException {
        int id = rs.getInt("ID");
        Date emit_date = rs.getDate("EMIT_DATE");
        double amount = rs.getDouble("AMOUNT");
        String description = rs.getString("DESCRIPTION");
        Expense expense = new Expense(id,amount,emit_date,description);
        expense.setCategory_id(rs.getInt("ID_CATEGORY"));
        expense.setSupplier_id(rs.getInt("ID_SUPPLIER"));
        expense.setUser_id(rs.getInt("ID_USER"));
        expense.setExpires(rs.getInt("EXPIRES"));
        return expense;
    }

    private void execute(Connection connection, String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private ExpenseResponseDto createExpenseRelationsObject(ResultSet rs) throws SQLException {
        ExpenseResponseDto obj = new ExpenseResponseDto();
        obj.setId(rs.getInt("ID"));
        obj.setDate(rs.getDate("EMIT_DATE"));
        obj.setAmount(rs.getDouble("AMOUNT"));
        obj.setDescription(rs.getString("DESCRIPTION"));
        List<Expiration> expirations = new ArrayList<>();
        if(rs.getArray("EXPIRATIONS") != null){
            Object[] expiration = (Object[]) rs.getArray("EXPIRATIONS").getArray();
            for (int i = 0; i < expiration.length;i++) {
                String string = expiration[i].toString();
                String[] data = string.substring(11).replace("[", "").replace("]", "").replace("'", "").split(", ");
                Expiration exp = new Expiration();
                exp.setId(Integer.parseInt(data[0].trim()));
                exp.setExpiration(Date.valueOf(data[1]));
                exp.setParticipation(Double.parseDouble(data[2]));
                expirations.add(exp);
            }
        }
        obj.setExpirations(expirations);
        Object[] categoryData = (Object[]) rs.getArray("CATEGORY").getArray();
        obj.setCategory(new Category(Integer.parseInt(categoryData[0].toString()), categoryData[1].toString()));
        Object[] supplier = (Object[]) rs.getArray("SUPPLIER").getArray();
        obj.setSupplier(new Supplier(Integer.parseInt(supplier[0].toString()), supplier[1].toString()));
        return obj;
    }
}
