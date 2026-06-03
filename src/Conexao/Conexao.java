package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:postgresql://ep-empty-dream-aqdzyhzb-pooler.c-8.us-east-1.aws.neon.tech/neondb?sslmode=require&channel_binding=require";
    private static final String USER = "neondb_owner";
    private static final String PASSWORD = "npg_SEpOd0t9BMXa";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

}
