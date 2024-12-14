package main.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import main.objects.Agent;
import main.util.StageUtil;
import java.io.IOException;

public class PropertyItemController {
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
    public DashboardController dashboardController;


    public void setParentController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    public void setData(Agent agent) {
        activeAgent = agent;
        agentName.setText(agent.getFirstname() + " " + agent.getLastname());
        agentCars.setText("Number of Assigned Cars: " + agent.getCars());
        agentRentedCars.setText("Cars Currently in Rent: " + agent);

        // TODO !!!
        // Make income method
        netIncome.setText("Net Income: $" +10000);
        agentAge.setText("Age: " + String.valueOf(agent.getAge()));
        agentAddress.setText(agent.getAddress());
        agentID.setText("Agent ID: " + agent.getAgentID());
    }

    public void onEditClick(ActionEvent event) throws IOException {
        if (!editOpen) {
            stage = new StageUtil("/fxml/editProperty.fxml");
            EditPropertyController controller = (EditPropertyController) stage.getController();
            controller.setDashboardController(dashboardController); // Pass DashboardController reference
            controller.initializeData(this);
            editOpen = true;
        }
    }

    public void setEditButton(boolean b) {
        editOpen = b;
    }

    /*
    public void onViewAgent() throws IOException {
        stage = new StageUtil("/fxml/registertenant.fxml");
        RegisterTenantController controller = (RegisterTenantController) stage.getController();
    }

     */

    @FXML
    void onViewAgent(ActionEvent event) {

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
