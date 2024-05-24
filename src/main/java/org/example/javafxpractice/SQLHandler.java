package org.example.javafxpractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLHandler {
    public static DatabaseConnection dbconnection = new DatabaseConnection();
    public static Connection connection1 = dbconnection.getConnection();


    public static void WriteUser(String firstname, String lastname, String username, String password) throws SQLException {
        Connection connection1 = DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12708922", "sql12708922", "1VJDbVskHr");

        String query = "INSERT INTO userlist " +
                "(firstname,lastname,username,password) " +
                "VALUES ('"+firstname+"','"+lastname+"','"+username+"','"+password+"');";

        Statement statement = connection1.createStatement();
        statement.executeUpdate(query);
    }





}
