package main.objects;

public class Agent {
    private String firstname;
    private String lastname;
    private int agentID;
    private double netIncome;
    private int occupiedCars;
    private int availableCars;

    public Agent(String firstname, String lastname, int agentID, double netIncome) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.agentID = agentID;
        this.netIncome = netIncome;
        this.occupiedCars = 0;
        this.availableCars = 0;
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
