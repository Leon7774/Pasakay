package org.example.javafxpractice.objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private static int userID;
    private static String firstName;
    private static String lastName;
    private static String userName;
    private static List<Property> propertyList;

    //TODO Property List
    public void addToPropertyList(ResultSet rs) throws SQLException {
        String propertyName = rs.getString("property_name");
        String pAddress = rs.getString("address");
        String description = rs.getString("description");
        int propertyID = rs.getInt("property_id");
        double monthly_income = rs.getDouble("monthly_income");
        double tax = rs.getDouble("monthly_tax");
        boolean isCommercial = rs.getBoolean("is_commercial");
        int availableUnits = rs.getInt("available_units");
        double unitMonthly = rs.getDouble("unit_monthly_price");
        int occupiedUnits = rs.getInt("occupiedUnits");
        double netIncome = rs.getDouble("net_income");

        Property property = new Property(propertyName, pAddress, description, isCommercial, tax, propertyID, availableUnits, netIncome, unitMonthly,occupiedUnits, netIncome);

        propertyList.add(property);
    }


    public static void setAccount(int userID2, String firstName2, String lastName2, String userName2) {
        userID = userID2;
        firstName = firstName2;
        lastName = lastName2;
        userName = userName2;

        propertyList = new ArrayList<>();
    }

    public static int getUserID() {
        return userID;
    }

    public static void setUserID(int userID) {
        Account.userID = userID;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        Account.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        Account.lastName = lastName;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        Account.userName = userName;
    }

    public static List<Property> getPropertyList() {
        return propertyList;
    }

    public static void setPropertyList(List<Property> propertyList) {
        Account.propertyList = propertyList;
    }

    public static void printActiveAccount() {
        System.out.println(Account.getFirstName());
    }

    public static Property findPropertyByID(int id) {
        for (Property a : propertyList) {
            if (a.getPropertyID() == id) {
                return a;
            }
        }
        return null;
    }
}
