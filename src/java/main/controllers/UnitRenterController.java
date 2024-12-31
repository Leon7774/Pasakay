package main.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.objects.Renter;

public class UnitRenterController {

    @FXML private Label renterIDLabel, renterNameLabel, renterSexLabel, renterAgeLabel,
            renterContactNumberLabel, renterLicenseNumberLabel, renterStatusLabel;
    @FXML private JFXButton editButton;

    private ViewRenterController parentController;

    @FXML
    void onEditClick(ActionEvent event) {

    }

    void setData(Renter renter) {

        renterNameLabel.setText(renter.getFirstName() + " " + renter.getLastName());
        renterIDLabel.setText("ID: " + renter.getRenterID());
        renterStatusLabel.setText("Status: " + renter.getStatus());
        renterSexLabel.setText("Sex: " + renter.getSex());
        renterAgeLabel.setText("Age: " + renter.getAge());
        renterContactNumberLabel.setText("Contact Number: " + renter.getContact_number());
        renterLicenseNumberLabel.setText("License Number: " + renter.getLicense_number());
    }

    void setParentController(ViewRenterController parentController) {
        this.parentController = parentController;
    }
}
