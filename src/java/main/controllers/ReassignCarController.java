package main.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.objects.Car;
import main.util.SQLHandlerUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReassignCarController {

    @FXML
    private JFXButton cancelButton;

    @FXML
    private TextField newAgentPrompt;

    @FXML
    private TextField oldAgentPrompt;

    @FXML
    private JFXButton reassignButton;

    private Car currentCar;
    private AgentViewController currentAgent;

    @FXML
    void onCancelClick(ActionEvent event) {

        ((Stage)reassignButton.getScene().getWindow()).close();
    }

    @FXML
    void onReassignClick(ActionEvent event) throws SQLException {

        if(SQLHandlerUtil.assignCar(currentCar.getCar_id(), Integer.parseInt(newAgentPrompt.getText().trim()))) {

            ((Stage)reassignButton.getScene().getWindow()).close();
        }
    }

    void setControllers(Car car, AgentViewController agentViewController) {

        this.currentCar = car;
        this.currentAgent = agentViewController;
        oldAgentPrompt.setText(String.valueOf(currentAgent.getActiveAgent().getAgentID()));
    }

}
