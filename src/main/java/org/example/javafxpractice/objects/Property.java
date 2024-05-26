package org.example.javafxpractice.objects;

public class Property {
    private String name;
    private String address;
    private String owner;
    private String description;
    private boolean isCommercial;
    private double tax;
    private int propertyID;
    private int availableUnits;
    private double income;
    private double unitMonthly;
    private int occupiedUnits;


    public double getUnitMonthly() {
        return unitMonthly;
    }

    public void setUnitMonthly(double unitMonthly) {
        this.unitMonthly = unitMonthly;
    }

    public Property(String name, String address, String description, boolean isCommercial, double tax, int propertyID, int availableUnits, double income, double unitMonthly, int occupiedUnits) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.isCommercial = isCommercial;
        this.tax = tax;
        this.propertyID = propertyID;
        this.availableUnits = availableUnits;
        this.income = occupiedUnits * unitMonthly;
        this.unitMonthly = unitMonthly;
        this.occupiedUnits = occupiedUnits;
    }

    public void editProperty(String name, String address, String description, double tax, int availableUnits, double unitMonthly) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.tax = tax;
        this.propertyID = propertyID;
        this.availableUnits = availableUnits;
        this.unitMonthly = unitMonthly;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public boolean isCommercial() {
        return isCommercial;
    }

    public void setCommercial(boolean commercial) {
        isCommercial = commercial;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public int getAvailableUnits() {
        return availableUnits;
    }

    public void setAvailableUnits(int availableUnits) {
        this.availableUnits = availableUnits;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public String toString() {
        return "Property Details:" +
                "\n  Name: " + name +
                "\n  Address: " + address +
                "\n  Owner: " + owner +
                "\n  Description: " + description +
                "\n  Commercial: " + (isCommercial ? "Yes" : "No") +
                "\n  Tax: " + tax +
                "\n  Property ID: " + propertyID +
                "\n  Available Units: " + availableUnits +
                "\n  Income: " + income +
                "\n  Unit Monthly: " + unitMonthly;
    }

    public int getOccupiedUnits() {
        return occupiedUnits;
    }

    public void setOccupiedUnits(int occupiedUnits) {
        this.occupiedUnits = occupiedUnits;
    }
}
