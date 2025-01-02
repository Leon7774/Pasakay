package main.controllers.recordsPanel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.objects.Agent;

import java.sql.SQLException;

public class UnitAgentController_Small {


    @FXML
    private Label agentID;

    @FXML
    private Label agentName;

    @FXML
    private Label netIncome;

    private Agent agent;
    private SelectAgentController controller;

    @FXML
    void choose(ActionEvent event) throws SQLException {
        controller.setAgent(agent);
        ((Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow()).close();
    }


    public void setData(Agent agent) {
        this.agent = agent;
        agentID.setText("ID: " + String.valueOf(agent.getAgentID()));
        agentName.setText(agent.getFirstname() + " " + agent.getLastname());
        netIncome.setText(String.valueOf(agent.getNetIncome()));
    }

    void setParentController(SelectAgentController controller) {
        this.controller = controller;
    }

}
