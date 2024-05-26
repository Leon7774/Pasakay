package org.example.javafxpractice.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection("jdbc:mysql://localhost/maindb", "root", "bitnull10");
        } catch (Exception e) {
            System.out.println("Yooo something went wrong");
            e.printStackTrace();

            e.getCause();
        }

        return databaseLink;
    }
}
