package main.controllers.prompts;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.controllers.views.DashboardMain;
import main.controllers.views.ViewRentalsController;
import main.controllers.views.ViewRenterController;
import main.objects.Account;
import main.objects.Rental;
import main.util.SQLHandlerUtil;

import java.sql.SQLException;

public class ConfirmPaymentController {

    @FXML
    private JFXButton cancelButton;

    @FXML
    private Label carName;

    @FXML
    private Label carName2;

    @FXML
    private JFXButton confirmButton;

    @FXML
    private Label depositValue;

    private double deposit;

    private Rental rental;

    private ViewRentalsController viewRentalsController;

    @FXML
    void onCancelClick(ActionEvent event) {
        ((Stage) cancelButton.getScene().getWindow()).close();
    }

    @FXML
    void onConfirmClick(ActionEvent event) throws SQLException {
        rental.setFully_paid(true);
        SQLHandlerUtil.setFullyPaid(rental.getId(), true);
        SQLHandlerUtil.addTransaction(rental.getId(), "Full Pay", rental.getTotalCost() * 0.8, DashboardMain.getCurrentDate().toString());
        refreshTable();
        ((Stage) cancelButton.getScene().getWindow()).close();
        SQLHandlerUtil.findUser(Account.getUserName());
    }

    public void setDeposit(double deposit) {
    	this.deposit = deposit;
    	this.depositValue.setText("$"+deposit);
    }

    public void setViewRentalsController(ViewRentalsController viewRentalsController) {
    	this.viewRentalsController = viewRentalsController;
    }

    public void refreshTable() throws SQLException {
    	viewRentalsController.initializeTable();
    }

    public void setRental(Rental rental) {
    	this.rental = rental;
    }

}
