package main.controllers.recordsPanel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.controllers.units.UnitAgentController;
import main.objects.Account;
import main.objects.Agent;

import java.io.IOException;
import java.sql.SQLException;

public class SelectAgentController {

    @FXML
    private ImageView searchButton;

    @FXML
    private TextField searchTab;

    @FXML
    private VBox vboxContent;

    private ViewRecordsController parentController;

    @FXML
    void initialize() {
        initializeTable();
    }

    @FXML
    void onCancelClick(ActionEvent event) {
        ((Stage)searchButton.getScene().getWindow()).close();
    }

    @FXML
    void search(KeyEvent event) {

    }

    @FXML
    void searchClicked(MouseEvent event) {

    }

    public void setAgent(Agent agent) throws SQLException {
        parentController.setAgent(agent);
        parentController.initializeTable();
    }

    public void initializeTable() {
        vboxContent.getChildren().clear();

        for (Agent agent : Account.getAgentList()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitAgent-Small.fxml"));
                // Makes a horizontal box for every agent
                HBox hbox = loader.load();
                // Grabs the controller of each agent hbox made -- This allows us to edit each hbox
                UnitAgentController_Small controller = loader.getController();
                controller.setData(agent);
                // Passes the dashboard controller to each hbox, so that when a component is accessed from the hbox, the dashboard will be editable
                controller.setParentController(this); // Without this, any user input that happened inside the hbox would not be able to affec the dashboard
                vboxContent.getChildren().add(hbox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void setParentController(ViewRecordsController parentController) {
        this.parentController = parentController;
    }

}
