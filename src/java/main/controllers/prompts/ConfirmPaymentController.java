package main.controllers.prompts;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.objects.Rental;
import main.util.SQLHandlerUtil;

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

    @FXML
    void onCancelClick(ActionEvent event) {
        ((Stage) cancelButton.getScene().getWindow()).close();
    }

    @FXML
    void onConfirmClick(ActionEvent event) {
        rental.setFully_paid(true);
        //SQLHandlerUtil.updateRental(rental);
        ((Stage) cancelButton.getScene().getWindow()).close();
    }

    public void setDeposit(double deposit) {
    	this.deposit = deposit;
    	this.depositValue.setText("$"+deposit);
    }

    public void setRental(Rental rental) {
    	this.rental = rental;
    }

}
