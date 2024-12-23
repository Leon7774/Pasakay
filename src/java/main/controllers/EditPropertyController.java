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
    private AgentsDashboardController dashboardController;
    private int agentID;

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

        agentID = propertyItemController.getActiveAgent().getAgentID();
    }

    @FXML
    public void applyButtonClicked(ActionEvent event) throws IOException, SQLException {

        int agentAge = Integer.parseInt(agentAgePrompt.getText());
        int agentContact = Integer.parseInt(agentContactPrompt.getText());
        String agentAddress = agentAddressPrompt.getText();
        String agentFirstName = agentFirstNamePrompt.getText();
        String agentLastName = agentLastNamePrompt.getText();

        if(SQLHandlerUtil.updateAgent(agentID, agentFirstName, agentLastName, agentAge, agentAddress, agentContact)) {

            propertyItemController.getActiveAgent().setFirstname(agentFirstName);
            propertyItemController.getActiveAgent().setLastname(agentLastName);
            propertyItemController.getActiveAgent().setAge(agentAge);
            propertyItemController.getActiveAgent().setContactNumber(agentContact);
            propertyItemController.getActiveAgent().setAddress(agentAddress);
        }

        dashboardController.getVbox().getChildren().clear();
        dashboardController.initializeTable();

        propertyItemController.setEditButton(false);
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void setDashboardController(AgentsDashboardController controller) {
        this.dashboardController = controller;
    }

}
