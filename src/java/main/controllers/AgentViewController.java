package main.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.objects.Agent;

public class AgentViewController {

    @FXML
    private JFXButton addPropertyButton1;

    @FXML
    private Text agentAddress;

    @FXML
    private Text agentAge;

    @FXML
    private Text agentContact;

    @FXML
    private Text agentID;

    @FXML
    private AnchorPane dashboardContent;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Text agentName;

    @FXML
    private VBox vboxContent;

    @FXML
    void propertyAdd(ActionEvent event) {

    }

    public void setAgentData(Agent agent) {
        // Set agent details
        agentName.setText("Name: " + agent.getFirstname() + " " + agent.getLastname());
        agentID.setText("ID: " + agent.getAgentID());
        agentAddress.setText("Address: " + agent.getAddress());
        agentAge.setText("Age: " + agent.getAge());
    }
}
