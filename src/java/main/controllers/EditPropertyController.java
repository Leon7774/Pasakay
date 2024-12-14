package main.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.objects.Account;
import main.util.SQLHandlerUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditPropertyController implements Initializable {

    @FXML
    private TextField agentAddressPrompt;

    @FXML
    private TextField agentAgePrompt;

    @FXML
    private TextField agentContactPrompt;

    @FXML
    private TextField agentFirstNamePrompt;

    @FXML
    private TextField agentLastNamePrompt;

    @FXML
    private JFXButton applyButton;

    @FXML
    private JFXButton cancelButton;

    private PropertyItemController propertyItemController;
    private DashboardController dashboardController;

    public void onCancelClick(ActionEvent event) {
        propertyItemController.setEditButton(false);
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");
    }

    public void initializeData(PropertyItemController controller) {
        this.propertyItemController = controller;

        agentFirstNamePrompt.setText(propertyItemController.getActiveAgent().getFirstname());
        agentLastNamePrompt.setText(propertyItemController.getActiveAgent().getLastname());
        agentAddressPrompt.setText(propertyItemController.getActiveAgent().getAddress());
        agentAgePrompt.setText(String.valueOf(propertyItemController.getActiveAgent().getAge()));
        agentContactPrompt.setText(String.valueOf(propertyItemController.getActiveAgent().getContactNumber()));
    }


    /*
    @FXML
    public void applyButtonClicked(ActionEvent event) throws IOException, SQLException {
        property = Account.findPropertyByID(Integer.parseInt(propertyItemController.propertyID.getText().split(" ")[1]));
        property.editProperty(
                propertyNamePrompt.getText(),
                propertyAddressPrompt.getText(),
                propertyDescriptionPrompt.getText(),
                Double.parseDouble(monthlyTaxField.getText()),
                Integer.parseInt(propertyAvailableUnitsPrompt.getText()),
                Double.parseDouble(propertyMonthlyPerUnitPrompt.getText())
        );

        SQLHandlerUtil.saveProperty(property);

        dashboardController.getVbox().getChildren().clear();
        dashboardController.initializeTable();

        propertyItemController.setEditButton(false);
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
     */

    public void setDashboardController(DashboardController controller) {
        this.dashboardController = controller;
    }

}
