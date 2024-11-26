package com.henry.expenseTracker.dao.impl;

import com.henry.expenseTracker.dao.IDao;
import com.henry.expenseTracker.db.impl.jdbcConfigH2;
import com.henry.expenseTracker.db.dbConnection;
import com.henry.expenseTracker.entity.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class categoryDaoH2 implements IDao<Category> {
    private dbConnection dbConnection;

    public categoryDaoH2(){
        this.dbConnection = new jdbcConfigH2();
    }
    public categoryDaoH2(dbConnection dbConnection){
        this.dbConnection = dbConnection;
    }

    private static final String FIND_ALL = "SELECT * FROM CATEGORY";
    private static final String FIND_ONE = "SELECT * FROM CATEGORY WHERE ID = ?";
    private static final String INSERT = "INSERT INTO CATEGORY(NAME,DESCRIPTION) VALUES ('%s','%s')";
    private static final String UPDATE = "UPDATE CATEGORY SET NAME='%s', SET DESCRIPTION='%s' WHERE ID='%s'";
    private static final String DELETE = "DELETE FROM CATEGORY WHERE ID = '%s'";

    public List<Category> findAll(){
        List<Category> categoryList = new ArrayList<>();
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                categoryList.add(createCategoryObject(rs));
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(String.valueOf(e));
        }
        return categoryList;
    }

    @Override
    public Optional<Category> findByPk(int id) {
        Connection connection = dbConnection.getConnection();
        Category category = null;
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_ONE);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                category = createCategoryObject(rs);
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category != null? Optional.of(category) : Optional.empty();
    }

    @Override
    public Category save(Category category) {
        Connection connection = dbConnection.getConnection();
        String query = String.format(INSERT, category.getName(), category.getDescription());
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                int id = keys.getInt(1);
                System.out.println(id);
                category.setId(id);
            }
            keys.close();
            stmt.close();
            connection.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public Category update(Category category) {
        Connection connection = dbConnection.getConnection();
        String query = String.format(UPDATE, category.getName(), category.getDescription(), category.getId());
        execute(connection,query);
        return category;
    }

    @Override
    public void delete(int id) {
        Connection connection = dbConnection.getConnection();
        String query = String.format(DELETE, id);
        execute(connection,query);
    }
    public Category createCategoryObject(ResultSet rs){
        Category category = new Category();
        try {
            category.setId(rs.getInt("ID"));
            category.setName(rs.getString("NAME"));
            category.setDescription(rs.getString("DESCRIPTION"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
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
}
