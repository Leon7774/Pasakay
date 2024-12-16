package main.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.objects.Agent;
import main.util.StageUtil;

import java.io.IOException;

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
    private JFXButton backButton;

    private Agent activeAgent;

    public DashboardController parentController;


    @FXML
    void carAdd(ActionEvent event) {

    }

    @FXML
    public void initialize() {
        System.out.println("Hello World");
    }

    @FXML
    void onBackClick(ActionEvent event) throws IOException {

        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();

        StageUtil stage = new StageUtil("/fxml/dashboard.fxml");
    }

    public void setAgentData(Agent agent) {
        this.activeAgent = agent;
        this.agentName.setText(activeAgent.getFirstname()+" "+activeAgent.getLastname());
        this.agentID.setText(String.valueOf(activeAgent.getAgentID()));
        this.agentAddress.setText(activeAgent.getAddress());
        this.agentAge.setText(String.valueOf(activeAgent.getAge()));
        this.agentContact.setText(String.valueOf(activeAgent.getContactNumber()));

        System.out.println("bruh");
    }

    public void setParentController(DashboardController parentController) {
        this.parentController = parentController;
    }
}
