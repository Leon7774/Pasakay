package main.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.objects.Account;
import main.objects.CarType;
import main.util.SQLHandlerUtil;

import java.io.IOException;
import java.sql.SQLException;

public class EditCarController {

    @FXML
    private JFXButton applyButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private TextField colorPrompt;

    @FXML
    private TextField dailyRatePrompt;

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

    private UnitCarController parentController;

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

        emptyWarningLabel.setVisible(false);
    }

    @FXML
    void onCancelClick(ActionEvent event) throws IOException {
        Stage stage = (Stage) typePrompt.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onApplyClick(ActionEvent event) throws SQLException {

        if (checkInput()) {
            int year = (int) yearPrompt.getValue();
            String color = colorPrompt.getText();
            String make = manufacturerPrompt.getValue();
            String model = modelPrompt.getText();
            String type = typePrompt.getValue();
            double dailyRate = Double.parseDouble(dailyRatePrompt.getText());

            if (color.length() > 15 || model.length() > 15 || make.length() > 15) {
                emptyWarningLabel.setVisible(true);
                emptyWarningLabel.setText("Color, Model, and Make must be less than 15 characters");
                return;
            }

            SQLHandlerUtil.updateCar(year, getCarTypeID(type), model, make, color, dailyRate, parentController.getCar().getCar_id());
            parentController.getParentController().getActiveAgent().setCars(SQLHandlerUtil.getAgentCars(parentController.getParentController().getActiveAgent().getAgentID()));

            // Refresh the table
            Stage stage = (Stage) emptyWarningLabel.getScene().getWindow();
            stage.close();

            parentController.getParentController().initializeTable();
        }
    }

    private boolean checkInput() {
        boolean valid = true;
        if (colorPrompt.getText().isBlank()) {
            valid = false;
        }
        if (modelPrompt.getText().isBlank()) {
            valid = false;
        }
        if (manufacturerPrompt.getValue() == null) {
            valid = false;
        }
        if (typePrompt.getValue() == null) {
            valid = false;
        }
        if (yearPrompt.getValue() == null) {
            valid = false;
        }
        emptyWarningLabel.setVisible(!valid);
        return valid;
    }

    private int getCarTypeID(String type) {
        for (CarType carType : Account.getCarTypeList()) {
            if (type.contains(carType.getType())) {
                return carType.getId();
            }
        }
        return -1;
    }

    private String getCarTypeName(int type_id) {

        for (CarType carType : Account.getCarTypeList()) {
            if (carType.getId() == type_id) {
                return carType.getType();
            }
        }
        return null;
    }

    public void setParentController(UnitCarController parentController) {
        this.parentController = parentController;
        populateFields();
    }

    void populateFields() {

        System.out.println(getCarTypeName(parentController.getCar().getCar_type_id()));

        colorPrompt.setText(parentController.getCar().getCar_color());
        dailyRatePrompt.setText(String.valueOf(parentController.getCar().getDailyRate()));
        manufacturerPrompt.getSelectionModel().select(parentController.getCar().getCar_make());
        modelPrompt.setText(parentController.getCar().getCar_model());
        typePrompt.getSelectionModel().select(getCarTypeName(parentController.getCar().getCar_type_id()).equals("Sedan") ? 0 : 1);
        yearPrompt.getSelectionModel().select((Integer) parentController.getCar().getCar_year());
    }
}
