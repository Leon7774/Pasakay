package main.objects;

import main.util.SQLHandlerUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Rental {

    private int rental_id, agentID, renterID, carID;
    private LocalDate rentStart, rentEnd;
    private double totalCost;
    private boolean fully_paid, deposited, returned;

    public Rental(int agentID, int renterID, int carID, LocalDate rentStart, LocalDate rentEnd, double totalCost, boolean fully_paid, boolean deposited,boolean returned) {
        this.agentID = agentID;
        this.renterID = renterID;
        this.carID = carID;
        this.rentStart = rentStart;
        this.rentEnd = rentEnd;
        this.totalCost = totalCost;
        this.fully_paid = fully_paid;
        this.deposited = deposited;
        this.returned = returned;
    }

    //Getters
    public int getId() {return this.rental_id;}
    public int getRenterID() {return this.renterID;}
    public LocalDate getRentStart() {return this.rentStart;}
    public LocalDate getRentEnd() {return this.rentEnd;}
    public int getAgentId() {return this.agentID;}
    public int getCarId() {return this.carID;}
    public double getTotalCost() {return this.totalCost;}
    public boolean isFullyPaid() {return this.fully_paid;}
    public boolean isDeposited() {return this.deposited;}
    public boolean isReturned() {return returned;}

    //Setters
    public void setId(int id) {this.rental_id = id;}
    public void setRenterID(int renterID) {this.renterID = renterID;}
    public void setRentStart(LocalDate rentStart) {this.rentStart = rentStart;}
    public void setRentEnd(LocalDate rentEnd) {this.rentEnd = rentEnd;}
    public void setAgentID(int agentID) {this.agentID = agentID;}
    public void setCarID(int carID) {this.carID = carID;}
    public void setTotalCost(double totalCost) {this.totalCost = totalCost;}
    public void setDeposited(boolean deposited) {this.deposited = deposited;}
    public void setFully_paid(boolean fully_paid) {this.fully_paid = fully_paid;}
    public void setReturned(boolean returned) {this.returned = returned;}

    @Override
    public int hashCode() {

        return Objects.hash(rental_id, renterID, rentStart, rentEnd);
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Rental rental = (Rental) obj;

        return  Objects.equals(rental_id, rental.getId()) &&
                Objects.equals(renterID, rental.getRenterID()) &&
                Objects.equals(rentStart, rental.getRentStart()) &&
                Objects.equals(rentEnd, rental.getRentEnd());
    }

    @Override
    public String toString() {
        return "Rentals{" +
                "rental_id=" + rental_id +
                ", renterID=" + renterID +
                ", rentStart=" + rentStart +
                ", rentEnd=" + rentEnd +
                ", agentID=" + agentID +
                ", carID=" + carID +
                ", totalCost=" + totalCost +
                ", deposited=" + deposited +
                ", fully_paid=" + fully_paid +
                ", returned=" + returned +
                '}';
    }





}
