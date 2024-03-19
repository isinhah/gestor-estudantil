import db.DB;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = DB.getConnection();

        if (conn != null) {
            System.out.println("Banco de dados conectado!");
        }
    }
}