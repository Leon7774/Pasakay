package main.controllers.prompts;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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

    @FXML
    void onCancelClick(ActionEvent event) {

    }

    @FXML
    void onConfirmClick(ActionEvent event) {

    }

    public void setDeposit(double deposit) {
    	this.deposit = deposit;
    	this.depositValue.setText("$"+deposit);
    }

}
