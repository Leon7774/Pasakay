package org.example.javafxpractice.objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private int userID;
    private String firstName;
    private String lastName;
    private String userName;
    private List<Property> propertyList;

    //TODO Property List
    public void addToPropertyList(ResultSet rs) throws SQLException {
        String pAddress = rs.getString("address");
        int puserID = rs.getInt("userID");
        String propertyName = rs.getString("property_name");
        int propertyID = rs.getInt("property_id");
        double netIncome = rs.getDouble("net_income");
        String description = rs.getString("description");


        Property property = new Property(propertyName, pAddress, description, )
        System.out.println(pAddress);

    }

    public Account(int userID, String firstName, String lastName, String userName) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;

        propertyList = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
