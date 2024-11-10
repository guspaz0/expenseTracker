package dao.impl;

import dao.categoryDao;
import dao.dto.categoryDto;
import db.impl.jdbcConfigH2;
import db.dbConnection;
import entity.Category;
import exceptions.categoryNotFoundException;
import exceptions.categoryRepeatedException;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class categoryDaoImplH2 implements categoryDao {
    dbConnection dbConnection = new jdbcConfigH2();
    private final Connection connection;

    public categoryDaoImplH2(){
        this.connection = dbConnection.getConnection();
    }

    public List<Category> findAll(){
        try {
            List<Category> categoryList = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM CATEGORY");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Category category = new Category();
                category.setId(rs.getInt("ID"));
                category.setName(rs.getString("NAME"));
                category.setDescription("DESCRIPTION");
                categoryList.add(category);
            }
            return categoryList;
        } catch (Exception e) {
            System.out.println(String.valueOf(e));
            return null;
        }
    }

    @Override
    public Category findByPk(int id) throws categoryNotFoundException {
        try {
            Category category = new Category();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM CATEGORY WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                category.setId(rs.getInt("ID"));
                category.setName(rs.getString("NAME"));
                category.setDescription(rs.getString("DESCRIPTION"));
                return category;
            } else {

                throw new categoryNotFoundException("category "+ id + "not found");
            }
        } catch (Exception e) {
            if (e instanceof categoryNotFoundException) {
                throw new categoryNotFoundException(e.getMessage());
            } else System.out.println(String.valueOf(e));;
            return null;
        }
    }

    @Override
    public void create(categoryDto categoryDto) throws categoryRepeatedException{
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO CATEGORY (NAME, DESCRIPTION) VALUES (?, ?)");
            ps.setString(1,categoryDto.getName());
            ps.setString(2,categoryDto.getDescription());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e){
            if (e instanceof JdbcSQLIntegrityConstraintViolationException) {
                throw new categoryRepeatedException("category name "+ categoryDto.getName() +" already exists");
            } else System.out.println(String.valueOf(e));
        }
    }

    @Override
    public void update(Category category) throws categoryRepeatedException {
        try {
            PreparedStatement ps=connection.prepareStatement("UPDATE CATEGORY SET NAME=?, DESCRIPTION=? WHERE ID=?");
            ps.setString(1,category.getName());
            ps.setString(2,category.getDescription());
            ps.setInt(3,category.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            if (e instanceof JdbcSQLIntegrityConstraintViolationException) {
                throw new categoryRepeatedException("category name already exists");
            }
            System.out.println(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM CATEGORY WHERE ID = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(String.valueOf(e));
        }
    }

}
