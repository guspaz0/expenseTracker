package db;

import java.sql.Connection;
import java.sql.SQLException;

public interface dbConnection {
    Connection getConnection();
}
