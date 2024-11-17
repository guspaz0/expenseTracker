package dao.impl;

import dao.IDao;
import db.dbConnection;
import db.impl.jdbcConfigH2;
import entity.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class paymentDaoH2 implements IDao<Payment> {
    private dbConnection dbConnection;

    public paymentDaoH2(){
        this.dbConnection = new jdbcConfigH2();
    }
    private static final String FIND_ALL = "SELECT * FROM PAYMENTS";
    private static final String FIND_BY_PK = "SELECT * FROM PAYMENTS WHERE ID = '%s'";
    private static final String CREATE = "INSERT INTO PAYMENTS(ID_USER,ID_SUPPLIER,AMOUNT,DATE) VALUES ('%s','%s','%s','%s')";
    private static final String DELETE = "DELETE FROM SUPPLIERS WHERE ID = '%s'";

    @Override
    public List<Payment> findAll() {
        Connection connection = dbConnection.getConnection();
        List<Payment> paymentList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                paymentList.add(createPaymentObject(rs));
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paymentList;
    }

    @Override
    public Payment update(Payment payment) {
        return null;
    }

    @Override
    public Optional<Payment> findByPk(int id) {
        Connection connection = dbConnection.getConnection();
        Payment payment = null;
        Statement stmt = null;
        String query = String.format(FIND_BY_PK, id);
        try {
            stmt = connection.createStatement();
            payment = createPaymentObject(stmt.executeQuery(query));
            stmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payment != null? Optional.of(payment): Optional.empty();
    }

    @Override
    public Payment save(Payment payment) {
        Connection connection = dbConnection.getConnection();
        Statement stmt = null;
        String query = String.format(CREATE, payment.getId_user(),payment.getId_supplier(), payment.getAmount(),payment.getDate());
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                payment.setId(keys.getInt(1));
            }
            keys.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payment;
    }

    @Override
    public void delete(int id) {
        Connection connection = dbConnection.getConnection();
        String query = String.format(DELETE, id);
        execute(connection,query);
    }

    private Payment createPaymentObject(ResultSet rs) throws SQLException {
        int id = rs.getInt("ID");
        int id_supplier = rs.getInt("ID_SUPPLIER");
        int id_user = rs.getInt("ID_USER");
        Date date = rs.getDate("DATE");
        double amount = rs.getDouble("AMOUNT");
        return new Payment(id,id_supplier,id_user,amount,date);
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
