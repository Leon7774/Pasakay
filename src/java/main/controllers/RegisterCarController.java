package main.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.objects.Car;
import main.util.SQLHandlerUtil;

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
    private JFXComboBox<String> manufacturerPrompt;

    @FXML
    private TextField modelPrompt;

    @FXML
    private JFXComboBox<String> typePrompt;

    @FXML
    private JFXComboBox<Integer> yearPrompt;

    private AgentViewController controller;

    @FXML
    private void initialize() {
        for (int year = 2000; year <= 2024; year++) {
            yearPrompt.getItems().add(year);
        }
    }

    @FXML
    void onCancelClick(ActionEvent event) {

    }

    @FXML
    void onRegisterClick(ActionEvent event) {
        if (checkInput()) {
            SQLHandlerUtil.addCar(controller.getActiveAgent().getAgentID(), (int) yearPrompt.getValue(), )
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
