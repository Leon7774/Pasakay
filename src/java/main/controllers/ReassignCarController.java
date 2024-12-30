package main.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.objects.Car;
import main.util.SQLHandlerUtil;

import java.sql.SQLException;

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
    private ViewAgentsController currentAgent;

    @FXML
    void onCancelClick(ActionEvent event) {

        ((Stage)reassignButton.getScene().getWindow()).close();
    }

    @FXML
    void onReassignClick(ActionEvent event) throws SQLException {

        try {
            if (SQLHandlerUtil.assignCar(currentCar.getCar_id(), Integer.parseInt(newAgentPrompt.getText().trim()))) {

                ((Stage) reassignButton.getScene().getWindow()).close();
                currentAgent.getActiveAgent().setCars(SQLHandlerUtil.getAgentCars(currentAgent.getActiveAgent().getAgentID()));
                currentAgent.initializeTable();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void setControllers(Car car, ViewAgentsController viewAgentsController) {

        this.currentCar = car;
        this.currentAgent = viewAgentsController;
        oldAgentPrompt.setText(String.valueOf(currentAgent.getActiveAgent().getAgentID()));
    }

}
