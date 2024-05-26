package org.example.javafxpractice.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.javafxpractice.objects.Account;
import org.example.javafxpractice.objects.Property;
import org.example.javafxpractice.util.FXMLLoaderUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditPropertyController implements Initializable {

    public TextField propertyNamePrompt;
    public TextField propertyAddressPrompt;
    public TextField propertyDescriptionPrompt;
    public TextField propertyAvailableUnitsPrompt;
    public TextField propertyMonthlyPerUnitPrompt;
    public TextField monthlyTaxField;
    public JFXButton applyButton;
    public JFXButton cancelButton;
    private PropertyItemController propertyItemController;
    private DashboardController dashboardController;
    private Property property;

    public void onCancelClick(ActionEvent event) {
        propertyItemController.setEditButton(false);
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");
    }

    //Gets data from propertyitemcontroller
    public void initializeData(PropertyItemController controller) {
        this.propertyItemController = controller;

        propertyNamePrompt.setText(propertyItemController.propertyName.getText());
        propertyAddressPrompt.setText(propertyItemController.propertyAddress.getText());
        propertyDescriptionPrompt.setText(propertyItemController.propertyDescription.getText());
        propertyAvailableUnitsPrompt.setText(propertyItemController.availableUnits.getText().split(" ")[2]);
        propertyMonthlyPerUnitPrompt.setText(propertyItemController.monthlyPerUnit.getText().split(" ")[4]);
        monthlyTaxField.setText(propertyItemController.monthlyTax.getText().split(" ")[2]);
    }

    @FXML
    public void applyButtonClicked(ActionEvent event) throws IOException {

        property = Account.findPropertyByID(Integer.parseInt(propertyItemController.propertyID.getText().split(" ")[1]));
        property.editProperty(
                propertyNamePrompt.getText(),
                propertyAddressPrompt.getText(),
                propertyDescriptionPrompt.getText(),
                Double.parseDouble(monthlyTaxField.getText().substring(1)),
                Integer.parseInt(propertyAvailableUnitsPrompt.getText()),
                Double.parseDouble(propertyMonthlyPerUnitPrompt.getText().substring(1)));

        dashboardController.getDashboardContent().getChildren().clear();
        dashboardController.getDashboardContent().getChildren().setAll(FXMLLoaderUtil.getInstance().load("/fxml/propertytab.fxml"));
        dashboardController.initializeTable();
    }

    public void setDashboardController(DashboardController controller) {
        this.dashboardController = controller;
    }

}
