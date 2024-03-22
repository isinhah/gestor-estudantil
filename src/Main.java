import db.DB;
import db.DbException;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        Connection conn = DB.getConnection();

        if (conn != null) {
            System.out.println("Banco de dados conectado!");
        }
    }
}