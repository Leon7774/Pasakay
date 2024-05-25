package org.example.javafxpractice.util;

import org.example.javafxpractice.objects.Account;

import java.sql.*;

public class SQLHandlerUtil {
    public static DatabaseConnection dbconnection = new DatabaseConnection();
    public static Connection connection1 = dbconnection.getConnection();


    public static void WriteUser(String firstname, String lastname, String username, String password) throws SQLException {

        String query = "INSERT INTO userlist " +
                "(firstname,lastname,username,password) " +
                "VALUES ('"+firstname+"','"+lastname+"','"+username+"','"+password+"');";

        Statement statement = connection1.createStatement();
        statement.executeUpdate(query);
    }

    //IMPORTANT! Returns user based on username
    public static void findUser(String username) throws SQLException {
        String query = "SELECT * FROM userlist WHERE username = '" + username + "'";

        Statement statement = connection1.createStatement();

        ResultSet rs = statement.executeQuery(query);

        // JDBC needs to move to next line before retrieving data, otherwise error will occur
        if (rs.next()) {
            int userID = rs.getInt("userID");
            String firstName = rs.getString("firstname");
            String lastName = rs.getString("lastname");
            String inusername = rs.getString("username");

            Account.setAccount(userID, firstName, lastName, inusername);

            System.out.println(Account.getFirstName());
        } else {
            System.out.println("User not found");
        }
    }
}

