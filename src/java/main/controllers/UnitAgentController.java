package main.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import main.objects.Account;
import main.objects.Agent;
import main.objects.Car;
import main.util.SQLHandlerUtil;
import main.util.StageUtil;
import java.io.IOException;
import java.sql.SQLException;

public class UnitAgentController {
    @FXML
    private Label agentAddress;

    @FXML
    private Label agentAge;

    @FXML
    private Label agentCars;

    @FXML
    private Label agentID;

    @FXML
    private Label agentName;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private JFXButton editButton;

    @FXML
    private Label netIncome;

    @FXML
    private Label agentRentedCars;

    @FXML
    private JFXButton viewAgent;

    private boolean editOpen = false;
    private StageUtil stage;
    private Agent activeAgent;
    public ViewAgentController dashboardController;


    public void setParentController(ViewAgentController dashboardController) {
        this.dashboardController = dashboardController;
    }

    public void setData(Agent agent) throws SQLException {
        activeAgent = agent;
        activeAgent.setCars(SQLHandlerUtil.getAgentCars(activeAgent.getAgentID()));
        agentName.setText(agent.getFirstname() + " " + agent.getLastname());
        agentCars.setText("Number of Assigned Cars: " + agent.getCars().size());

        // TODO !!!
        // Make income method
        netIncome.setText("Net Income: $" +10000);
        agentAge.setText("Age: " + String.valueOf(agent.getAge()));
        agentAddress.setText(agent.getAddress());
        agentID.setText("Agent ID: " + agent.getAgentID());
    }

    public void onEditClick(ActionEvent event) throws IOException {
        if (!editOpen) {

            Stage currentStage = (Stage)((Node)event.getSource()).getScene().getWindow();

            stage = new StageUtil("/fxml/editAgent.fxml", currentStage);
            EditPropertyController controller = (EditPropertyController) stage.getController();
            controller.setDashboardController(dashboardController); // Pass DashboardController reference
            controller.initializeData(this);
            editOpen = true;
        }
    }

    @FXML
    public void onDeleteClick(ActionEvent event) throws SQLException, IOException {

        boolean carsInRent = false;

        for(Car car : activeAgent.getCars()) {

            if (car.getCar_currentlyRented()) {

                carsInRent = true;
            }
        }

        if (carsInRent) {

            new Alert(Alert.AlertType.ERROR, "Cars in this agent is currently rented",  ButtonType.OK).show();
        }

        else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Agent Deletion");
            alert.setContentText("Are you sure you want to delete this agent?");
            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

            if(result == ButtonType.OK) {

                System.out.println(SQLHandlerUtil.deleteAgent(activeAgent.getAgentID()));
                SQLHandlerUtil.findUser(Account.getUserName());
            }

            dashboardController.refreshTable();
        }
    }

    public void setEditButton(boolean b) {
        editOpen = b;
    };

    @FXML
    void onViewAgent(ActionEvent event) throws SQLException {
        if (dashboardController != null && activeAgent != null) {
            dashboardController.showAgentDetails(activeAgent);
        }
    }

    // FXML Getters
    public Label getAgentAddress() {
        return agentAddress;
    }

    public Label getAgentAge() {
        return agentAge;
    }

    public Label getAgentCars() {
        return agentCars;
    }

    public Label getAgentID() {
        return agentID;
    }

    public Label getAgentName() {
        return agentName;
    }

    public JFXButton getDeleteButton() {
        return deleteButton;
    }

    public JFXButton getEditButton() {
        return editButton;
    }

    public Label getNetIncome() {
        return netIncome;
    }

    public Label getAgentRentedCars() {
        return agentRentedCars;
    }

    public JFXButton getViewAgent() {
        return viewAgent;
    }

    public Agent getActiveAgent() {
        return activeAgent;
    }
}
