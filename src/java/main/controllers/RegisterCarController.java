package main.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.objects.Car;

public class RegisterCarController {

    @FXML
    private JFXButton applyButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private TextField colorPrompt;

    @FXML
    private Text emptyWarningLabel;

    @FXML
    private JFXComboBox<?> manufacturerPrompt;

    @FXML
    private TextField modelPrompt;

    @FXML
    private JFXComboBox<?> typePrompt;

    @FXML
    private JFXComboBox<?> yearPrompt;

    private AgentViewController controller;

    @FXML
    void onCancelClick(ActionEvent event) {

    }

    @FXML
    void onRegisterClick(ActionEvent event) {
        if (checkInput()) {
            //Car car = new Car()
        }
    }

    private boolean checkInput() {
        if (colorPrompt.getText().equals("")
            || modelPrompt.getText().equals("")
            || manufacturerPrompt.equals(null)
            || typePrompt.equals(null)
            || yearPrompt.equals(null)){
            emptyWarningLabel.setVisible(true);
            return false;
        }

        return true;
    }

    public void setParentController(AgentViewController controller) {
        this.controller = controller;
    }

}
