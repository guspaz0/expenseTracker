package dao.impl;

import dao.userDao;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import db.impl.jdbcConfigH2;
import db.dbConnection;
import dao.dto.userDto;
import exceptions.categoryNotFoundException;
import exceptions.expenseNotFoundException;
import exceptions.userEmailRepeatedException;
import exceptions.userNotFoundException;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;


public class userDaoImplH2 implements userDao {

    dbConnection dbConnection = new jdbcConfigH2();
    private final Connection connection;

    public userDaoImplH2(){
        this.connection = dbConnection.getConnection();
    }

    @Override
    public List<User> findAll() {
        try {
            List<User> lista = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM USERS");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User usr = new User();
                usr.setId(rs.getInt("ID"));
                usr.setName(rs.getString("NAME"));
                usr.setEmail(rs.getString("EMAIL"));
                lista.add(usr);
            }
            rs.close();
            ps.close();
            return lista;
        } catch(SQLException e) {
            System.out.println(String.valueOf(e));
            return null;
        }
    }

    @Override
    public User findByPk(int id) {
        try {
            User user = new User();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM USERS WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("ID"));
                user.setName(rs.getString("NAME"));
                user.setEmail(rs.getString("EMAIL"));
                rs.close();
                ps.close();
                return user;
            } else {
                throw new userNotFoundException("user id "+ id +" not found");
            }
        } catch (Exception e) {
            if (e instanceof userNotFoundException) {
                throw new userNotFoundException(e.getMessage());
            } else System.out.println(String.valueOf(e));;
            return null;
        }
    }

    @Override
    public void create(userDto userDto) throws userEmailRepeatedException {
        try {
            User newUser = new User();
            newUser.setName(userDto.getName());
            newUser.setEmail(userDto.getEmail());

            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO USERS(NAME,EMAIL) VALUES(?,?)");
            ps.setString(1,newUser.getName());
            ps.setString(2,newUser.getEmail());
            ps.executeUpdate();
            ps.close();
        } catch(Exception e) {
            if (e instanceof JdbcSQLIntegrityConstraintViolationException) {
                throw new userEmailRepeatedException("the email "+ userDto.getEmail() +" already exists");
            } else System.out.println(String.valueOf(e));
        }
    }

    @Override
    public void update(User user) {
        try {
            PreparedStatement ps=connection.prepareStatement("UPDATE USERS SET NAME=?, EMAIL=? WHERE ID=?");
            ps.setString(1,user.getName());
            ps.setString(2,user.getEmail());
            ps.setInt(3,user.getId());
            ps.executeUpdate();
            ps.close();
        } catch(SQLException e) {
            System.out.println(String.valueOf(e));
        }

    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM USERS WHERE ID = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(String.valueOf(e));
        }
    }

}