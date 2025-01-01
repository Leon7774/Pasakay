package main.controllers.prompts;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.controllers.units.UnitRentalController;
import main.controllers.views.DashboardMain;
import main.util.SQLHandlerUtil;

import java.sql.SQLException;

public class ConfirmCarReturnController {

    @FXML
    private JFXButton cancelButton;

    @FXML
    private Label carName;

    @FXML
    private Label carName2;

    @FXML
    private JFXButton confirmButton;

    private UnitRentalController parentController;

    @FXML
    void onCancelClick(ActionEvent event) {

        ((Stage)confirmButton.getScene().getWindow()).close();
    }

    @FXML
    void onConfirmClick(ActionEvent event) throws SQLException {

        SQLHandlerUtil.setCarReturned(parentController.getCurrentRental().getCarId(), true);
        SQLHandlerUtil.addTransaction(parentController.getCurrentRental().getId(), "Car Returned", 0.0, DashboardMain.getCurrentDate().toString());
        ((Stage)confirmButton.getScene().getWindow()).close();
    }

    public void setCarName(String carName) {

        this.carName.setText(carName);
    }

    public void setParentController(UnitRentalController parentController) {this.parentController = parentController;}
}
