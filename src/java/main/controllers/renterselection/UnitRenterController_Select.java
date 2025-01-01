package main.controllers.renterselection;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.objects.Renter;

public class UnitRenterController_Select {

    @FXML
    private JFXButton editButton;

    @FXML
    private Label renterAgeLabel;

    @FXML
    private Label renterContactNumberLabel;

    @FXML
    private Label renterIDLabel;

    @FXML
    private Label renterLicenseNumberLabel;

    @FXML
    private Label renterNameLabel;

    @FXML
    private Label renterSexLabel;

    @FXML
    private Label renterStatusLabel;

    private SelectRenterController parentController;

    @FXML
    void onEditClick(ActionEvent event) {

    }

    public void setData(Renter renter) {
        renterNameLabel.setText(renter.getFirstName() + " " + renter.getLastName());
        renterIDLabel.setText("ID: " + renter.getRenterID());
        renterStatusLabel.setText("Status: " + renter.getStatus());
        renterSexLabel.setText("Sex: " + renter.getSex());
        renterAgeLabel.setText("Age: " + renter.getAge());
        renterContactNumberLabel.setText("Contact Number: " + renter.getContact_number());
        renterLicenseNumberLabel.setText("License Number: " + renter.getLicense_number());
    }

    public void setParentController(SelectRenterController parentController) {
        this.parentController = parentController;
    }


    @FXML
    void onSelectClick(ActionEvent event) {
        //parentController.
    }


}
