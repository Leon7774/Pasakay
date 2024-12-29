package main.objects;

import java.time.LocalDate;
import java.util.Objects;

public class Rentals {

    private int rental_id, agentID, renterID, carID;
    private LocalDate rentStart, rentEnd;
    private double totalCost;

    public Rentals(int agentID, int renterID, int carID, LocalDate rentStart, LocalDate rentEnd, double totalCost) {
        this.agentID = agentID;
        this.renterID = renterID;
        this.carID = carID;
        this.rentStart = rentStart;
        this.rentEnd = rentEnd;
        this.totalCost = totalCost;
    }

    //Getters
    public int getId() {return this.rental_id;}
    public int getRenterID() {return this.renterID;}
    public LocalDate getRentStart() {return this.rentStart;}
    public LocalDate getRentEnd() {return this.rentEnd;}
    public int getAgentId() {return this.agentID;}
    public int getCarId() {return this.carID;}
    public double getTotalCost() {return this.totalCost;}

    //Setters
    public void setId(int id) {this.rental_id = id;}
    public void setRenterID(int renterID) {this.renterID = renterID;}
    public void setRentStart(LocalDate rentStart) {this.rentStart = rentStart;}
    public void setRentEnd(LocalDate rentEnd) {this.rentEnd = rentEnd;}
    public void setAgentID(int agentID) {this.agentID = agentID;}
    public void setCarID(int carID) {this.carID = carID;}
    public void setTotalCost(double totalCost) {this.totalCost = totalCost;}

    @Override
    public int hashCode() {

        return Objects.hash(rental_id, renterID, rentStart, rentEnd);
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Rentals rental = (Rentals) obj;

        return  Objects.equals(rental_id, rental.getId()) &&
                Objects.equals(renterID, rental.getRenterID()) &&
                Objects.equals(rentStart, rental.getRentStart()) &&
                Objects.equals(rentEnd, rental.getRentEnd());
    }
}
