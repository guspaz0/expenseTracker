package dao.impl;

import dao.userDao;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import db.jdbcConfiguration;
import dao.dto.userDto;


public class userDaoImplH2 implements userDao {

    private final Connection connection;

    public userDaoImplH2(){
        this.connection = jdbcConfiguration.getConnection();
    }

    @Override
    public List<User> getAllUsers() {
        try {
            List<User> lista = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM USERS");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User usr = new User();
                usr.setName(rs.getString("name"));
                usr.setEmail(rs.getString("email"));
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
                user.setName(rs.getString("NAME"));
                user.setEmail(rs.getString("EMAIL"));
                return user;
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public void registerUser(userDto userDto) {
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
        } catch( SQLException e) {
            System.out.println(String.valueOf(e));
        }
    }

    @Override
    public void updateUser(User persona) {

    }

    @Override
    public void deleteUser(int id) {

    }
}