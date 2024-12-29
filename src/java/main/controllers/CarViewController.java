package main.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import main.objects.Car;
import main.objects.Rentals;
import main.util.SQLHandlerUtil;

import java.sql.SQLException;

public class CarViewController {

    @FXML private Label agentIDLabel, renterIDLabel, carColorLabel, carIDLabel, carName, isRentedLabel, netIncomeLabel, noSeatsLabel,
                        rentEndLabel, rentStartLabel;
    @FXML private JFXButton deleteButton, editButton;

    private ViewRentalsController viewRentalsController;
    private Rentals currentRental;

    @FXML
    void onDeleteClick(ActionEvent event) throws SQLException {

        System.out.println(currentRental.getId());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Rental Deletion");
        alert.setContentText("Are you sure you want to delete this rental?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if(result == ButtonType.OK) {

            System.out.println(SQLHandlerUtil.deleteRental(currentRental.getId(), currentRental.getRenterID()));
            viewRentalsController.initializeTable();
        }
    }

    void setData(Car car, Rentals rental) throws SQLException {

        System.out.println(car.getCar_currentlyRented());

        this.carName.setText(car.getCar_model());
        this.carIDLabel.setText("Car ID: " + String.valueOf(car.getCar_id()));
        this.carColorLabel.setText("Color: " + car.getCar_color());
        this.noSeatsLabel.setText("Number of Seats: " + String.valueOf(SQLHandlerUtil.getCarType(car.getCar_type_id()).getPassengerCount()+ 1));
        this.isRentedLabel.setText("Currently Rented: " + (car.getCar_currentlyRented() ? "Yes" : "No"));
        this.agentIDLabel.setText("Agent ID: " + String.valueOf(rental.getAgentId()));
        this.renterIDLabel.setText("Renter ID: " + String.valueOf(rental.getRenterID()));
        this.rentStartLabel.setText("Rent Start Date: " + String.valueOf(rental.getRentStart()));
        this.rentEndLabel.setText("Rent End Date: " + String.valueOf(rental.getRentEnd()));
        this.netIncomeLabel.setText("Expected Income: $" + String.valueOf(rental.getTotalCost()));
        currentRental = rental;
    }

    void setParentController(ViewRentalsController viewRentalsController) {
        this.viewRentalsController = viewRentalsController;
    }

}

