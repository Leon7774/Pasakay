package main.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.objects.Account;
import main.objects.Agent;
import main.util.StageUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewAgentController implements Initializable {

    @FXML
    private AnchorPane dashboardContent;
    @FXML
    private VBox vboxContent;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ImageView closeButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initializeTable();
        scrollPane.setStyle("-fx-background: white;");
    }

    // Initializes the list of agents in the dashboard
    public void initializeTable() {
        // Disables horizontal scrolling
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        for (Agent agent : Account.getAgentList()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitAgent.fxml"));
                // Makes a horizontal box for every agent
                HBox hbox = loader.load();
                // Grabs the controller of each agent hbox made -- This allows us to edit each hbox
                UnitAgentController pipController = loader.getController();
                pipController.setData(agent);
                // Passes the dashboard controller to each hbox, so that when a component is accessed from the hbox, the dashboard will be editable
                pipController.setParentController(this); // Without this, any user input that happened inside the hbox would not be able to affec the dashboard
                vboxContent.getChildren().add(hbox);
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void dilightClose(MouseEvent event) {
        closeButton.setImage(new Image("/images/close-highlight.png"));
    }


    @FXML
    void highlightClose(MouseEvent event) {
        closeButton.setImage(new Image("/images/close.png"));
    }

    public void closeButtonOnActionEvent(MouseEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void showAgentDetails(Agent agent) throws SQLException {
        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/viewCars.fxml"));
            BorderPane agentDetailsPane = loader.load();

            // Set agent data in the AgentDetailsController
            ViewCarController controller = loader.getController();
            controller.setAgentData(agent);

            // Passes the dashboard controller to the agent view controller
            controller.setParentController(this);

            // Replace the current content with the agent details
            dashboardContent.getChildren().setAll(agentDetailsPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refreshTable() throws IOException {
        vboxContent.getChildren().clear();
        initializeTable();
    }

    public VBox getVbox() {
        return vboxContent;
    }

    @FXML
    public void agentAdd() throws IOException {
        StageUtil addAgent = new StageUtil("/fxml/registerAgent.fxml", (Stage)(dashboardContent.getScene().getWindow()));
        RegisterAgentController controller = (RegisterAgentController) addAgent.getController();
        controller.setDashboardController(this);
    }
}

