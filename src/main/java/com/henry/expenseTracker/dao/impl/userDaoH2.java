package com.henry.expenseTracker.dao.impl;

import com.henry.expenseTracker.dao.IDao;
import com.henry.expenseTracker.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.henry.expenseTracker.db.impl.jdbcConfigH2;
import com.henry.expenseTracker.db.dbConnection;


public class userDaoH2 implements IDao<User> {
    private dbConnection dbConnection;

    public userDaoH2(){
        this.dbConnection = new jdbcConfigH2();
    }
    private static final String FIND_ALL = "SELECT * FROM USERS";
    private static final String FIND_BY_PK = "SELECT * FROM USERS WHERE ID = '%s'";
    private static final String INSERT = "INSERT INTO USERS(NAME,EMAIL,PASSWORD) VALUES('%s','%s','%s')";
    private static final String UPDATE = "UPDATE USERS SET NAME='%s' EMAIL='%s' WHERE ID='%s'";
    private static final String DELETE = "DELETE FROM USERS WHERE ID = '%s'";

    @Override
    public List<User> findAll() {
        Connection connection = dbConnection.getConnection();
        List<User> userlist = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userlist.add(createObject(rs));
            }
            rs.close();
            ps.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return userlist;
    }

    @Override
    public Optional<User> findByPk(int id) {
        Connection connection = dbConnection.getConnection();
        User user = null;
        String query = String.format(FIND_BY_PK, id);
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                user = createObject(rs);
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user != null? Optional.of(user) : Optional.empty();
    }

    @Override
    public User save(User user) {
        Connection connection = dbConnection.getConnection();
        String query = String.format(INSERT, user.getName(), user.getEmail(), user.getPassword());
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                user.setId(keys.getInt(1));
            }
            stmt.close();
            keys.close();
            connection.close();
        } catch(Exception e) {
//            if (e instanceof JdbcSQLIntegrityConstraintViolationException) {
//                throw new userEmailRepeatedException("the email "+ userDto.getEmail() +" already exists");
//            } else System.out.println(String.valueOf(e));
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User update(User user) {
        Connection connection = dbConnection.getConnection();
        String query = String.format(UPDATE, user.getName(), user.getEmail(), user.getId());
        try {
            execute(connection,query);
        } catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void delete(int id) {
        Connection connection = dbConnection.getConnection();
        String query = String.format(DELETE, id);
        execute(connection,query);
    }
    private User createObject(ResultSet rs) {
        User user = new User();
        try {
            user.setId(rs.getInt("ID"));
            user.setName(rs.getString("NAME"));
            user.setEmail(rs.getString("EMAIL"));
            user.setPassword(rs.getString("PASSWORD"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
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