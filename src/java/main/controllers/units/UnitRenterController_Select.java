package main.controllers.units;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.controllers.prompts.EditRenterController;
import main.controllers.views.ViewRenterController;
import main.objects.Renter;
import main.util.StageUtil;

import java.io.IOException;

public class UnitRenterController_Select {

    @FXML private Label renterIDLabel, renterNameLabel, renterSexLabel, renterAgeLabel,
            renterContactNumberLabel, renterLicenseNumberLabel, renterStatusLabel;
    @FXML private JFXButton editButton;

    private Renter renter;
    private ViewRenterController parentController;


    @FXML
    void onEditClick(ActionEvent event) throws IOException {

        StageUtil newStage = new StageUtil("/fxml/editRenter.fxml", (Stage)editButton.getScene().getWindow());
        EditRenterController editRenterController = (EditRenterController) newStage.getController();
        editRenterController.setData(renter);
        editRenterController.setViewRenterController(parentController);
    }

    public void setData(Renter renter) {

        this.renter = renter;

        renterNameLabel.setText(renter.getFirstName() + " " + renter.getLastName());
        renterIDLabel.setText("ID: " + renter.getRenterID());
        renterStatusLabel.setText("Status: " + renter.getStatus());
        renterSexLabel.setText("Sex: " + renter.getSex());
        renterAgeLabel.setText("Age: " + renter.getAge());
        renterContactNumberLabel.setText("Contact Number: " + renter.getContact_number());
        renterLicenseNumberLabel.setText("License Number: " + renter.getLicense_number());
    }

    public void setParentController(ViewRenterController parentController) {this.parentController = parentController;}
}
