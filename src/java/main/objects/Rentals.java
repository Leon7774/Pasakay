package main.objects;

import java.time.LocalDate;
import java.util.Objects;

public class Rentals {

    private int id, renterID;
    private LocalDate rentStart, rentEnd;

    public Rentals(int id, int renterID, LocalDate rentStart, LocalDate rentEnd) {

        this.id = id;
        this.renterID = renterID;
        this.rentStart = rentStart;
        this.rentEnd = rentEnd;
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
