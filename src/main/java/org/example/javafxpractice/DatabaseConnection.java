package org.example.javafxpractice;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12708922", "sql12708922", "1VJDbVskHr");
        } catch (Exception e) {
            System.out.println("Yooo something went wrong");
            e.printStackTrace();

            e.getCause();
        }

        return databaseLink;
    }
}
