package com.henry.expenseTracker.dao.impl;

import com.henry.expenseTracker.dao.IDao;
import com.henry.expenseTracker.db.dbConnection;
import com.henry.expenseTracker.db.impl.jdbcConfigH2;
import com.henry.expenseTracker.entity.Supplier;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class supplierDaoH2 implements IDao<Supplier> {
    private dbConnection dbConnection;

    public supplierDaoH2(){
        this.dbConnection = new jdbcConfigH2();
    }
    public supplierDaoH2(dbConnection dbConnection){
        this.dbConnection = dbConnection;
    }

    private static final String FIND_ALL = "SELECT * FROM SUPPLIERS";
    private static final String FIND_BY_PK = "SELECT * FROM SUPPLIERS WHERE ID = ?";
    private static final String CREATE = "INSERT INTO SUPPLIERS(NAME) VALUES (?)";
    private static final String UPDATE = "UPDATE SUPPLIERS SET NAME=? WHERE ID=?";
    private static final String DELETE = "DELETE FROM SUPPLIERS WHERE ID = ?";


    @Override
    public List<Supplier> findAll() {
        List<Supplier> suppliers = new ArrayList<>();
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                suppliers.add(new Supplier(rs.getInt("ID"),rs.getString("NAME")));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    @Override
    public Optional<Supplier> findByPk(int id) {
        Connection connection = dbConnection.getConnection();
        Supplier supplier = null;
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_BY_PK);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                supplier = new Supplier(rs.getInt("ID"), rs.getString("NAME"));
            }
            rs.close();
            ps.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return supplier != null? Optional.of(supplier) : Optional.empty();
    }

    @Override
    public Supplier save(Supplier supplier) {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(CREATE);
            ps.setString(1,supplier.getName());
            ps.executeUpdate();
            supplier.setId(ps.getGeneratedKeys().getInt(1));
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supplier;
    }

    @Override
    public Supplier update(Supplier supplier) {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE);
            ps.setString(1,supplier.getName());
            ps.setInt(2,supplier.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supplier;
    }

    @Override
    public void delete(int id) {
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE);
            ps.setInt(1,id);
            ps.executeUpdate();
            ps.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
