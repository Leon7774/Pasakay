package main.controllers.renterselection;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ConfirmDepositController {

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
    private boolean isConfirmed = false;

    @FXML
    void onCancelClick(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
        isConfirmed = false;
    }

    @FXML
    void onConfirmClick(ActionEvent event) {
        isConfirmed = true;
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setDepositValue(double value) {
        depositValue.setText(String.valueOf(value));
    }
}
