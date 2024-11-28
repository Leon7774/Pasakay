package org.example.javafxpractice.util;

import org.example.javafxpractice.objects.Account;
import org.example.javafxpractice.objects.Property;
import org.example.javafxpractice.objects.Tenant;

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
            double balance = rs.getDouble("balance");

            Account.setAccount(userID, firstName, lastName, inusername, balance);

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
                double monthly_income = rs2.getDouble("monthly_income");
                double netIncome = rs2.getDouble(("net_income"));
                double unit_monthly_price = rs2.getDouble("unit_monthly_price");
                boolean isCommercial = rs2.getBoolean("is_Commercial");
                double monthly_tax = rs2.getDouble("monthly_tax");
                int occupiedUnits = rs2.getInt("occupiedUnits");

                Property property = new Property(
                        propertyName,
                        propertyAddress,
                        propertyDescription,
                        isCommercial,
                        monthly_tax,
                        propertyID,
                        availableUnits,
                        monthly_income,
                        unit_monthly_price,
                        occupiedUnits,
                        netIncome
                        );

                Account.getPropertyList().add(property);
            }

        } else {
            System.out.println("User not found");
        }
    }

    public static void saveProperty(Property property) throws SQLException {
        String name = property.getName();
        String address = property.getAddress();
        String description = property.getDescription();
        boolean isCommercial = property.isCommercial();
        double tax = property.getTax();
        int propertyID = property.getPropertyID();
        int availableUnits = property.getAvailableUnits();
        double income = property.getOccupiedUnits() * property.getUnitMonthly();
        double unitMonthly = property.getUnitMonthly();
        int occupiedUnits = property.getOccupiedUnits();
        double netIncome = property.getNetIncome();

        String sql = "UPDATE properties SET \n" +
                "property_name = ?,\n" +
                "address = ?,\n" +
                "description = ?,\n" +
                "net_income = ?,\n" +
                "is_commercial = ?,\n" +
                "monthly_income = ?,\n" +
                "monthly_tax = ?,\n" +
                "available_units = ?,\n" +
                "unit_monthly_price = ?,\n" +
                "occupiedUnits = ?\n" +
                "WHERE property_id = ?";

        PreparedStatement preparedStatement = connection1.prepareStatement(sql);

        // Set the parameters

        // Name
        preparedStatement.setString(1, name);
        //Address
        preparedStatement.setString(2, address);
        //Description
        preparedStatement.setString(3, description);
        //NetIncome
        preparedStatement.setDouble(4, netIncome);
        //Is Commercial
        preparedStatement.setBoolean(5, isCommercial);
        //Monthly Income
        preparedStatement.setDouble(6, (unitMonthly*occupiedUnits));
        //Monthly Tax
        preparedStatement.setDouble(7, tax);
        //Available Units
        preparedStatement.setInt(8, availableUnits);
        //Unit Monthly Price
        preparedStatement.setDouble(9, unitMonthly);
        //Occupied Units
        preparedStatement.setInt(10, occupiedUnits);
        //Property ID
        preparedStatement.setInt(11, propertyID);

        preparedStatement.executeUpdate();

    }

    public static void propertyAdd(String name, String address, String description, double tax, double monthly, int units) throws SQLException {
        String sql = "INSERT INTO properties (property_name,address,description,net_income,is_commercial,monthly_income,monthly_tax,available_units,unit_monthly_price,occupiedUnits,userID) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?);";

        PreparedStatement preparedStatement = connection1.prepareStatement(sql);

        // Name
        preparedStatement.setString(1, name);
        //Address
        preparedStatement.setString(2, address);
        //Description
        preparedStatement.setString(3, description);
        //NetIncome
        preparedStatement.setDouble(4, 0);
        //Is Commercial
        preparedStatement.setBoolean(5, true);
        //Monthly Income
        preparedStatement.setDouble(6, 0);
        //Monthly Tax
        preparedStatement.setDouble(7, tax);
        //Available Units
        preparedStatement.setInt(8, units);
        //Unit Monthly Price
        preparedStatement.setDouble(9, monthly);
        //Occupied Units
        preparedStatement.setInt(10, 0);
        //Owner ID
        preparedStatement.setInt(11, Account.getUserID());


        preparedStatement.executeUpdate();

    }

    public Property searchSQLByID(int ID) throws SQLException {
        String sql = "SELECT * FROM userlist WHERE userID = " + ID;

        Statement statement = connection1.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        Property property;

        while(rs.next()) {
            String propertyName = rs.getString("property_name");
            int propertyID = rs.getInt("property_id");
            String propertyAddress = rs.getString("address");
            String propertyDescription = rs.getString("description");
            int availableUnits = rs.getInt("available_units");
            double monthly_income = rs.getDouble("monthly_income");
            double netIncome = rs.getDouble(("net_income"));
            double unit_monthly_price = rs.getDouble("unit_monthly_price");
            boolean isCommercial = rs.getBoolean("is_Commercial");
            double monthly_tax = rs.getDouble("monthly_tax");
            int occupiedUnits = rs.getInt("occupiedUnits");

            property = new Property(
                    propertyName,
                    propertyAddress,
                    propertyDescription,
                    isCommercial,
                    monthly_tax,
                    propertyID,
                    availableUnits,
                    monthly_income,
                    unit_monthly_price,
                    occupiedUnits,
                    netIncome
            );

            return property;
        }

        return null;
    }

    public static void saveTenant(String firstname, String lastname, int age, String status, String sex, int propertyID) throws SQLException {
        String SQL = "INSERT INTO tenants(firstname,lastname, age, status, sex, propertyID) VALUES(?,?,?,?,?,?)";
        PreparedStatement statement = connection1.prepareStatement(SQL);
        statement.setString(1, firstname);
        statement.setString(2, lastname);
        statement.setInt(3, age);
        statement.setString(4, status);
        statement.setString(5, sex);
        statement.setInt(6, propertyID);
        statement.executeUpdate();
    }

    public static Property getLatestProperty() throws SQLException {
        String sql = "SELECT * FROM properties ORDER BY property_id DESC LIMIT 1;";

        Statement statement = connection1.createStatement();
        ResultSet rs2 = statement.executeQuery(sql);

        while(rs2.next()) {
            String propertyName = rs2.getString("property_name");
            int propertyID = rs2.getInt("property_id");
            String propertyAddress = rs2.getString("address");
            String propertyDescription = rs2.getString("description");
            int availableUnits = rs2.getInt("available_units");
            double monthly_income = rs2.getDouble("monthly_income");
            double netIncome = rs2.getDouble(("net_income"));
            double unit_monthly_price = rs2.getDouble("unit_monthly_price");
            boolean isCommercial = rs2.getBoolean("is_Commercial");
            double monthly_tax = rs2.getDouble("monthly_tax");
            int occupiedUnits = rs2.getInt("occupiedUnits");

            Property property = new Property(
                    propertyName,
                    propertyAddress,
                    propertyDescription,
                    isCommercial,
                    monthly_tax,
                    propertyID,
                    availableUnits,
                    monthly_income,
                    unit_monthly_price,
                    occupiedUnits,
                    netIncome
            );

            return property;
        }
        return null;
    }
}

