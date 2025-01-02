package main.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.controllers.units.UnitAgentController;
import main.controllers.views.ViewAgentController;
import main.util.SQLHandlerUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class EditAgentController implements Initializable {

    @FXML
    private TextField agentAddressPrompt;

    @FXML
    private DatePicker birthdatePrompt;

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
        birthdatePrompt.setValue(unitAgentController.getActiveAgent().getBirthdate());
        agentContactPrompt.setText(String.valueOf(unitAgentController.getActiveAgent().getContactNumber()));

        agentID = unitAgentController.getActiveAgent().getAgentID();
    }

    @FXML
    public void applyButtonClicked(ActionEvent event) throws IOException, SQLException {

        LocalDate date = birthdatePrompt.getValue();
        int agentContact = Integer.parseInt(agentContactPrompt.getText());
        String agentAddress = agentAddressPrompt.getText();
        String agentFirstName = agentFirstNamePrompt.getText();
        String agentLastName = agentLastNamePrompt.getText();

        if(SQLHandlerUtil.updateAgent(agentID, agentFirstName, agentLastName, date, agentAddress, agentContact)) {

            unitAgentController.getActiveAgent().setFirstname(agentFirstName);
            unitAgentController.getActiveAgent().setLastname(agentLastName);
            unitAgentController.getActiveAgent().setBirthdate(date);
            unitAgentController.getActiveAgent().setAge(Period.between(date, LocalDate.now()).getYears());
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
