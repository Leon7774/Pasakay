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
    private Object parentController;

    @FXML
    void onCancelClick(ActionEvent event) {

        ((Stage)reassignButton.getScene().getWindow()).close();
    }

    @FXML
    void onReassignClick(ActionEvent event) throws SQLException {

        try {
            if (SQLHandlerUtil.assignCar(currentCar.getCar_id(), Integer.parseInt(newAgentPrompt.getText().trim()))) {

                ((Stage) reassignButton.getScene().getWindow()).close();

                if(parentController instanceof ViewCarController) {

                    ViewCarController viewCarController = (ViewCarController) parentController;
                    viewCarController.getActiveAgent().setCars(SQLHandlerUtil.getAgentCars(viewCarController.getActiveAgent().getAgentID()));
                    viewCarController.initializeTable();
                }

                else if(parentController instanceof ViewInactiveCarsController) {

                    ViewInactiveCarsController viewCarController = (ViewInactiveCarsController) parentController;
                    viewCarController.initializeTable();
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void setControllers(Car car, Object parentController) {

        this.currentCar = car;
        this.parentController = parentController;

        if(parentController instanceof ViewCarController) {

            ViewCarController viewCarController = (ViewCarController) parentController;
            oldAgentPrompt.setText(String.valueOf(viewCarController.getActiveAgent().getAgentID()));
        }

        else if(parentController instanceof ViewInactiveCarsController) {

            oldAgentPrompt.setText("None");
            oldAgentPrompt.setDisable(true);
        }
    }

}
