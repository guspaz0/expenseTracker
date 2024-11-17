package dao.impl;

import dao.IDao;
import db.dbConnection;
import db.impl.jdbcConfigH2;
import entity.Expiration;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ExpirationDaoH2 implements IDao<Expiration> {
    private dbConnection dbConnection;

    public ExpirationDaoH2(){
        this.dbConnection = new jdbcConfigH2();
    }
    public ExpirationDaoH2(dbConnection dbConnection){
        this.dbConnection = dbConnection;
    }

    private static final String FIND_ALL = "SELECT * FROM EXPIRATIONS WHERE ID_EXPENSE = ?";
    private static final String FIND_ONE = "SELECT * FROM EXPIRATIONS WHERE ID = ?";
    private static final String ADD_EXPIRATION = "INSERT INTO EXPIRATIONS(ID_EXPENSE, EXPIRATION, PARTICIPATION) VALUES ('%s','%s','%s')";
    private static final String UPDATE = "UPDATE EXPIRATIONS SET EXPIRATION='%s', PARTICIPATION='%s' WHERE ID='%s'";
    private static final String DELETE = "DELETE FROM EXPIRATIONS WHERE ID = '%s'";

    @Override
    public List<Expiration> findAll() {
        Connection connection = dbConnection.getConnection();
        List<Expiration> expirationList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                expirationList.add(createExpenseExpirationObject(rs));
            }
            rs.close();
            ps.close();
            connection.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return expirationList;
    }

    @Override
    public Optional<Expiration> findByPk(int id) {
        Expiration expenseExp = null;
        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_ONE);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                expenseExp = createExpenseExpirationObject(rs);
            }
            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expenseExp != null? Optional.of(expenseExp) : Optional.empty();
    }

    @Override
    public Expiration save(Expiration Expiration) {
        Connection connection = dbConnection.getConnection();
        Statement stmt =null;
        String query = String.format(ADD_EXPIRATION, Expiration.getExpenseId(), Expiration.getExpiration(), Expiration.getParticipation());
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = stmt.getGeneratedKeys();
            Expiration.setId(keys.getInt(1));
            stmt.close();
            connection.close();
            keys.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return Expiration;
    }

    @Override
    public Expiration update(Expiration Expiration) {
        Connection connection = dbConnection.getConnection();
        Statement stmt = null;
        String query = String.format(UPDATE, Expiration.getExpiration(), Expiration.getExpiration(), Expiration.getParticipation());
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Expiration;
    }

    @Override
    public void delete(int id) {
        Connection connection = dbConnection.getConnection();
        String query = String.format(DELETE,id);
        execute(connection,query);
    }

    private Expiration createExpenseExpirationObject(ResultSet rs) throws SQLException {
        int id = rs.getInt("ID");
        int id_expense = rs.getInt("ID_EXPENSE");
        Date expiration = rs.getDate("EXPIRATION");
        double participation = rs.getDouble("PARTICIPATION");
        return new Expiration(id,id_expense,expiration,participation);
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
