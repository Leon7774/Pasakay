package main.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RenterViewController {

    @FXML private Label rentalIDLabel, carIDLabel, renterIDLabel, renterNameLabel, renterSexLabel, renterAgeLabel,
                  renterContactNumberLabel, renterLicenseNumberLabel, renterStatusLabel;
    @FXML private JFXButton editButton;

    @FXML
    void onEditClick(ActionEvent event) {

    }

    void setData(String name, int id, String status, String sex, int age, String contactNumber, String licenseNumber, int carID, int rentalID) {

        renterNameLabel.setText(name);
        renterIDLabel.setText("ID: " + Integer.toString(id));
        renterStatusLabel.setText("Status: " + status);
        renterSexLabel.setText("Sex: " + sex);
        renterAgeLabel.setText("Age: " + age);
        renterContactNumberLabel.setText("Contact Number: " + contactNumber);
        renterLicenseNumberLabel.setText("License Number: " + licenseNumber);
        carIDLabel.setText("Rented Car ID: " + Integer.toString(carID));
        rentalIDLabel.setText("Rental ID: " + Integer.toString(rentalID));
    }
}
