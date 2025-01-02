package main.controllers.prompts;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.controllers.units.UnitRentalController;
import main.controllers.views.DashboardMain;
import main.controllers.views.ViewRentalsController;
import main.objects.Account;
import main.objects.Rental;
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

    private ViewRentalsController viewRentalsController;

    private Rental rental;

    @FXML
    void onCancelClick(ActionEvent event) {

        ((Stage)confirmButton.getScene().getWindow()).close();
    }

    @FXML
    void onConfirmClick(ActionEvent event) throws SQLException {
        rental.setReturned(true);
        rental.setFully_paid(true);
        SQLHandlerUtil.setCarReturned(parentController.getCurrentRental().getId(), true);
        SQLHandlerUtil.addTransaction(parentController.getCurrentRental().getId(), "Car Returned", 0.0, DashboardMain.getCurrentDate().toString());
        refreshTable();
        ((Stage)confirmButton.getScene().getWindow()).close();
        SQLHandlerUtil.findUser(Account.getUserName());
    }

    public void setCarName(String carName) {
        this.carName.setText(carName);
    }

    public  void setViewRentalsController(ViewRentalsController viewRentalsController) {
        this.viewRentalsController = viewRentalsController;
    }

    public void refreshTable(){
        viewRentalsController.initializeTable();
    }

    public void setParentController(UnitRentalController parentController) {this.parentController = parentController;}

    public void setRental(Rental rental) {
        this.rental = rental;
    }
}
