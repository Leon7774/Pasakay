package main.objects;

import java.time.LocalDate;
import java.util.Objects;

public class RentalTransaction {

    int rentalID;
    String transactionName, date;
    double amount;

    public RentalTransaction(int rentalID, String transactionName, double amount, String date) {

        this.rentalID = rentalID;
        this.transactionName = transactionName;
        this.amount = amount;
        this.date = date;
    }

    public void setRentalID(int rentalID) {this.rentalID = rentalID;}
    public void setTransactionName(String transactionName) {this.transactionName = transactionName;}
    public void setAmount(double amount) {this.amount = amount;}
    public void setDate(String date) {this.date = date;}

    public String getTransactionName() {return this.transactionName;}
    public double getAmount() {return this.amount;}
    public String getDate() {return this.date;}
    public int getRentalID() {return this.rentalID;}

    public int hashCode() {

        return Objects.hash(rentalID, transactionName, amount, date);
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        RentalTransaction transaction = (RentalTransaction) obj;

        return  Objects.equals(rentalID, transaction.getRentalID()) &&
                Objects.equals(transactionName, transaction.getTransactionName()) &&
                Objects.equals(amount, transaction.getAmount()) &&
                Objects.equals(date, transaction.getDate());
    }
}
