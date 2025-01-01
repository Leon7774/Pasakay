package main.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.controllers.units.UnitAgentController;
import main.controllers.views.ViewAgentController;
import main.util.SQLHandlerUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditAgentController implements Initializable {

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

    private UnitAgentController unitAgentController;
    private ViewAgentController dashboardController;
    private int agentID;

    public void onCancelClick(ActionEvent event) {
        unitAgentController.setEditButton(false);
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");
    }

    public void initializeData(UnitAgentController controller) {
        this.unitAgentController = controller;

        agentFirstNamePrompt.setText(unitAgentController.getActiveAgent().getFirstname());
        agentLastNamePrompt.setText(unitAgentController.getActiveAgent().getLastname());
        agentAddressPrompt.setText(unitAgentController.getActiveAgent().getAddress());
        agentAgePrompt.setText(String.valueOf(unitAgentController.getActiveAgent().getAge()));
        agentContactPrompt.setText(String.valueOf(unitAgentController.getActiveAgent().getContactNumber()));

        agentID = unitAgentController.getActiveAgent().getAgentID();
    }

    @FXML
    public void applyButtonClicked(ActionEvent event) throws IOException, SQLException {

        int agentAge = Integer.parseInt(agentAgePrompt.getText());
        int agentContact = Integer.parseInt(agentContactPrompt.getText());
        String agentAddress = agentAddressPrompt.getText();
        String agentFirstName = agentFirstNamePrompt.getText();
        String agentLastName = agentLastNamePrompt.getText();

        if(SQLHandlerUtil.updateAgent(agentID, agentFirstName, agentLastName, agentAge, agentAddress, agentContact)) {

            unitAgentController.getActiveAgent().setFirstname(agentFirstName);
            unitAgentController.getActiveAgent().setLastname(agentLastName);
            unitAgentController.getActiveAgent().setAge(agentAge);
            unitAgentController.getActiveAgent().setContactNumber(agentContact);
            unitAgentController.getActiveAgent().setAddress(agentAddress);
        }

        dashboardController.getVbox().getChildren().clear();
        dashboardController.initializeTable();

        unitAgentController.setEditButton(false);
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void setDashboardController(ViewAgentController controller) {
        this.dashboardController = controller;
    }

}
