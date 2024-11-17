package db.impl;

import db.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jdbcConfigH2 implements dbConnection {

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:tcp://localhost:1521/db_expenses;INIT=RUNSCRIPT FROM 'db_expenses.sql'";


    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION, "sa", "test");
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(String.valueOf(ex));
        }
        return connection;
    }

}
