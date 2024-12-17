package main.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.objects.Car;
import main.util.SQLHandlerUtil;
import main.objects.CarType;
import main.objects.Account;
import main.util.SceneUtil;

import java.io.IOException;
import java.sql.SQLException;

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

    private String[] brands = new String[] {"Hyundai", "Toyota", "BMW", "Ford", "Nissan", "Kia", "Subaru", "Mitsubishi"};

    @FXML
    private void initialize() {
        for (int year = 2000; year <= 2024; year++) {
            yearPrompt.getItems().add(year);
        }

        manufacturerPrompt.getItems().addAll(brands);
        for (CarType carType : Account.getCarTypeList()) {
            typePrompt.getItems().add(carType.getPassengerCount() + "-seater " + carType.getTerrain() + " " +carType.getType());
        }

    }

    @FXML
    void onCancelClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) typePrompt.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onRegisterClick(ActionEvent event) throws SQLException {
        if (checkInput()) {
            int year = (int) yearPrompt.getValue();
            String color = colorPrompt.getText();
            String make = manufacturerPrompt.getValue();
            String model = modelPrompt.getText();
            String type = typePrompt.getValue();


            Car car = SQLHandlerUtil.addCar(controller.getActiveAgent().getAgentID(), year, getCarTypeID(type), false, model, make, color, 0);
            controller.getActiveAgent().getCars().add(car);

            // Refresh the table
            Stage stage = (Stage) emptyWarningLabel.getScene().getWindow();
            stage.close();

            SQLHandlerUtil.findUser(Account.getUserName());

            controller.initializeTable();
        }
    }

    private int getCarTypeID(String type) {
        for (CarType carType : Account.getCarTypeList()) {
            if (type.contains(carType.getType())) {
                return carType.getId();
            }
        }
        return -1;
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
