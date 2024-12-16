package main.objects;

import javafx.scene.control.ListCell;

import java.util.ArrayList;
import java.util.List;

public class Agent {
    private String firstname;
    private String lastname;
    private int agentID;
    private double netIncome;
    private int contactNumber;
    private String address;
    private int age;
    private int occupiedCars;
    private int availableCars;
    private List<Car> cars;


    public Agent(String firstname, String lastname, int agentID, String address, int age, int contactNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.agentID = agentID;
        this.occupiedCars = 0;
        this.availableCars = 0;
        this.contactNumber = contactNumber;
        this.address = address;
        this.age = age;
        this.netIncome = 0;
        this.cars = new ArrayList<>();
    }

    public Agent(String firstname, String lastname, int age, String address, int contactNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.occupiedCars = 0;
        this.availableCars = 0;
        this.contactNumber = contactNumber;
        this.address = address;
        this.age = age;
        this.netIncome = 0;
        this.cars = new ArrayList<>();
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAgentID() {
        return agentID;
    }

    public void setAgentID(int agentID) {
        this.agentID = agentID;
    }

    public double getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(double netIncome) {
        this.netIncome = netIncome;
    }

    public int getOccupiedCars() {
        return occupiedCars;
    }

    public void setOccupiedCars(int occupiedCars) {
        this.occupiedCars = occupiedCars;
    }

    public int getAvailableCars() {
        return availableCars;
    }

    public void setAvailableCars(int availableCars) {
        this.availableCars = availableCars;
    }

    public void addOccupiedCar() {
        this.occupiedCars++;
    }
}
