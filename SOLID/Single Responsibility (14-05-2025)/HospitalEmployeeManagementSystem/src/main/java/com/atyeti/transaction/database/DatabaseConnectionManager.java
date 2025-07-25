package com.atyeti.transaction.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class DatabaseConnectionManager {
    private Connection conn;
    private static DatabaseConnectionManager connectionInstance=new DatabaseConnectionManager();
    private DatabaseConnectionManager() {
    }
    public static DatabaseConnectionManager getManagerInstance() {
        return connectionInstance;
    }


    public void connect() throws SQLException {
        conn = DriverManager.getConnection("Some/Database/URL");
        System.out.println("Established Database Connection...");
    }
    public Connection getConnectionObject() {
        return conn;
    }

}
