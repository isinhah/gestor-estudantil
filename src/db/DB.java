package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
    private static String url = "jdbc:mysql://localhost:3306/estudantesjdbc";
    private static String user = "root";
    private static String password = "admin";

    public DB() {}

    public static Connection getConnection() throws SQLException {
        Connection connection;
        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }
}
