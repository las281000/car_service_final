package com.laukhina.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection extends AbstractJdbcConnection {
    // Database credentials
    private String DB_URL;
    private String USER;
    private String PASS;

    public JdbcConnection() {
        DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
        USER = "postgres";
        PASS = "281000";
    }

    public Connection getConnection() {
        return connection;
    }

    public void makeConnection() {
        try {
           Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
