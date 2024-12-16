package main.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.objects.Account;
import main.objects.Agent;
import main.objects.Car;
import main.util.FXMLLoaderUtil;
import main.util.StageUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private JFXListView<Label> listview;
    private Stage stage;

    @FXML
    private AnchorPane dashboardContent;
    @FXML
    private VBox vboxContent;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private JFXButton addPropertyButton;
    @FXML
    private Label welcomeLabel;
    private static boolean opened = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO FIX LABEL
        setWelcomeLabel();
        initializeTable();
        welcomeLabel.setText("Welcome, " + Account.getFirstName());
    }

    public void setWelcomeLabel() {
        if (!opened) {
            welcomeLabel.setText("Welcome, " + Account.getFirstName());
            opened = true;
        }
    }

    public void initializeTable() {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        for (Agent agent : Account.getAgentList()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/propertyunit.fxml"));
                HBox hbox = loader.load();
                PropertyItemController pipController = loader.getController();
                pipController.setData(agent);
                pipController.setParentController(this); // Set parent controller
                vboxContent.getChildren().add(hbox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void showAgentDetails(Agent agent) {
        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AgentView.fxml"));
            BorderPane agentDetailsPane = loader.load();

            // Set agent data in the AgentDetailsController
            AgentViewController controller = loader.getController();
            controller.setAgentData(agent);

            // Passes the dashboard controller to the agent view controller
            controller.setParentController(this);

            // Replace the current content with the agent details
            dashboardContent.getChildren().setAll(agentDetailsPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onAgentTabClick(ActionEvent event) {

    }

    public void onLogoutTabClick(ActionEvent event) {
        // Close the current stage
        Stage currentStage = (Stage) dashboardContent.getScene().getWindow();
        currentStage.close();

        // Launch a new login stage
        Platform.runLater(() -> {
            try {
                StageUtil util = new StageUtil("/fxml/login.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void refreshTable() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/propertytab.fxml"));
        BorderPane propertyTabPane = loader.load(); // Load the content first
        dashboardContent.getChildren().setAll(propertyTabPane);
        initializeTable();
    }

    public VBox getVbox() {
        return vboxContent;
    }


    @FXML
    public void agentAdd() throws IOException {
        StageUtil addAgent = new StageUtil("/fxml/registerAgent.fxml");
        RegisterPropertyController controller = (RegisterPropertyController) addAgent.getController();
        controller.setDashboardController(this);
    }


}

