package org.example.javafxpractice.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.javafxpractice.objects.Account;
import org.example.javafxpractice.objects.Property;
import org.example.javafxpractice.util.SQLHandlerUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterPropertyController implements Initializable {

    public TextField registerName;
    public TextField registerAddress;
    public TextField registerDescription;
    public TextField registerUnits;
    public TextField registerMonthly;
    public TextField registerTax;
    public JFXButton applyButton;
    public JFXButton cancelButton;
    public Label emptyWarningLabel;
    public DashboardController dashboardController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (dashboardController == null) {
            System.out.println("It is inded null retard");
        }
    }

    public void setDashboardController(DashboardController controller) {
        this.dashboardController = controller;
    }

    public void applyButtonClicked(ActionEvent event) throws SQLException {
        if (
                registerName.getText().isBlank() ||
                        registerAddress.getText().isBlank() ||
                        registerDescription.getText().isBlank() ||
                        registerTax.getText().isBlank() ||
                        registerMonthly.getText().isBlank() ||
                        registerUnits.getText().isBlank()
        ) {
            emptyWarningLabel.setVisible(true);
        } else {
            SQLHandlerUtil.propertyAdd(registerName.getText(), registerAddress.getText(), registerDescription.getText(), Double.parseDouble(registerTax.getText()), Double.parseDouble(registerMonthly.getText()), Integer.parseInt(registerUnits.getText()));
            Account.getPropertyList().add(SQLHandlerUtil.getLatestProperty());

            //TODO make account successfully handled
            Stage stage = (Stage) emptyWarningLabel.getScene().getWindow();
            stage.close();

            dashboardController.getVbox().getChildren().clear();
            dashboardController.initializeTable();
        }

    }



    public void onCancelClick(ActionEvent event) {
        Stage stage = (Stage) registerName.getScene().getWindow();
        stage.close();
    }


}
