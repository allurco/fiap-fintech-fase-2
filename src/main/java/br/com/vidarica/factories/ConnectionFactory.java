package br.com.vidarica.factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionFactory {

    @org.jetbrains.annotations.Nullable
    public static Connection getConnection() throws SQLException {
        //connect to the database
        return DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "RM559300", "130476");
    }
}
