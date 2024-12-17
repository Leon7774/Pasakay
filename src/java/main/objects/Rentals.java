package main.objects;

import java.time.LocalDate;
import java.util.Objects;

public class Rentals {

    private int id, agentID, renterID, carID;
    private LocalDate rentStart, rentEnd;
    private double totalCost;


    public Rentals(int id, int agentID, int renterID, int carID, LocalDate rentStart, LocalDate rentEnd, double totalCost) {

        this.id = id;
        this.agentID = agentID;
        this.renterID = renterID;
        this.carID = carID;
        this.rentStart = rentStart;
        this.rentEnd = rentEnd;
        this.totalCost = totalCost;
    }

    //Getters
    public int getId() {return this.id;}
    public int getRenterID() {return this.renterID;}
    public LocalDate getRentStart() {return this.rentStart;}
    public LocalDate getRentEnd() {return this.rentEnd;}

    //Setters
    public void setId(int id) {this.id = id;}
    public void setRenterID(int renterID) {this.renterID = renterID;}
    public void setRentStart(LocalDate rentStart) {this.rentStart = rentStart;}
    public void setRentEnd(LocalDate rentEnd) {this.rentEnd = rentEnd;}

    @Override
    public int hashCode() {

        return Objects.hash(id, renterID, rentStart, rentEnd);
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Rentals rental = (Rentals) obj;

        return  Objects.equals(id, rental.getId()) &&
                Objects.equals(renterID, rental.getRenterID()) &&
                Objects.equals(rentStart, rental.getRentStart()) &&
                Objects.equals(rentEnd, rental.getRentEnd());
    }
}
