package com.pluralsight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    // this class connects to the database
    private static final String url = "jdbc:mysql://localhost:3306/car_dealership?user=root&password=Northwest101$";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }
}
