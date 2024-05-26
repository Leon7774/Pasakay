package org.example.javafxpractice.util;

import org.example.javafxpractice.objects.Account;
import org.example.javafxpractice.objects.Property;

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

            String propTableQuery = "SELECT * FROM properties JOIN userlist " +
                    "ON properties.userID = userlist.userID \n" +
                    "WHERE userlist.userID = " + Account.getUserID();

            ResultSet rs2 = statement.executeQuery(propTableQuery);

            //TODO Finish this shit
            while(rs2.next()) {
                String propertyName = rs2.getString("property_name");
                int propertyID = rs2.getInt("property_id");
                String propertyAddress = rs2.getString("address");
                String propertyDescription = rs2.getString("description");
                int availableUnits = rs2.getInt("available_units");
                double net_income = rs2.getDouble("net_income");
                double unit_monthly_price = rs2.getDouble("unit_monthly_price");
                boolean isCommercial = rs2.getBoolean("is_Commercial");
                double monthly_tax = rs2.getDouble("monthly_tax");
                int occupiedUnits = rs2.getInt("occupied_units");

                Property property = new Property(
                        propertyName,
                        propertyAddress,
                        propertyDescription,
                        isCommercial,
                        monthly_tax,
                        propertyID,
                        availableUnits,
                        net_income,
                        unit_monthly_price,
                        occupiedUnits
                        );
                Account.getPropertyList().add(property);
            }

            for (Property a :Account.getPropertyList()) {
                System.out.println(a.toString());
            }



        } else {
            System.out.println("User not found");
        }
    }
}

