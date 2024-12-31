package main.objects;

import main.controllers.DashboardMain;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Car {

    private int car_id, car_year, car_type_id;
    private boolean car_currentlyRented;
    private double dailyRate;
    private String car_model, car_color, make;
    private double totalIncome;
    private int totalRentDays;

    public Car(int car_year, int car_type_id, boolean car_currentlyRented, String car_model, String make, String car_color, double dailyRate) {
        this.car_year = car_year;
        this.car_type_id = car_type_id;
        this.car_currentlyRented = car_currentlyRented;
        this.car_model = car_model;
        this.car_color = car_color;
        this.make = make;
        this.dailyRate = dailyRate;
    }

    // Getters
    public int getCar_id() {return car_id;}
    public int getCar_year() {return car_year;}
    public int getCar_type_id() {return car_type_id;}
    public boolean getCar_currentlyRented() {return car_currentlyRented;}
    public String getCar_model() {return car_model;}
    public String getCar_color() {return car_color;}
    public String getCar_make() {return make;}
    public double getDailyRate() {return dailyRate;}

    // Setters
    public void setCar_id(int car_id) {this.car_id = car_id;}
    public void setCar_year(int car_year) {this.car_year = car_year;}
    public void setCar_type_id(int car_type_id) {this.car_type_id = car_type_id;}
    public void setCar_currentlyRented(boolean car_currentlyRented) {this.car_currentlyRented = car_currentlyRented;}
    public void setCar_model(String car_model) {this.car_model = car_model;}
    public void setCar_color(String car_color) {this.car_color = car_color;}
    public void setDailyRate(double dailyRate) {this.dailyRate = dailyRate;}

    @Override
    public int hashCode() {
        return Objects.hash(car_id, car_year, car_type_id);
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Car car = (Car) obj;

        return  Objects.equals(car_id, car.getCar_id()) &&
                Objects.equals(car_year, car.getCar_year()) &&
                Objects.equals(car_type_id, car.getCar_type_id()) &&
                Objects.equals(car_model, car.getCar_model()) &&
                Objects.equals(car_color, car.getCar_color());
    }

    public boolean checkIfRented() {
        boolean rented = false;
        for(Rental rental : Account.getRentalsList()) {
            if(rental.getCarId() == car_id) {
                if (rental.getRentStart().isBefore(DashboardMain.getCurrentDate()) && rental.getRentEnd().isAfter(DashboardMain.getCurrentDate())) {
                    rented = true;
                    System.out.println("Rented");
                    break;
                }
            }
        }
        return rented;
    }
}