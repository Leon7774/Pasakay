package main.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.objects.Account;
import main.objects.Car;
import main.util.SQLHandlerUtil;
import main.util.StageUtil;

import java.io.IOException;
import java.sql.SQLException;

public class UnitCarController {

    @FXML
    private Label carColor;

    @FXML
    private Label carID;

    @FXML
    private Label carName;

    @FXML
    private Label carSeats;

    @FXML
    private Label carType;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private JFXButton editButton;

    @FXML
    private JFXButton reassignButton;

    @FXML
    private Label netIncome;

    @FXML
    private JFXButton scheduleRental;

    private ViewAgentsController parentController;
    private Car car;

        @FXML
        void onEditClick(ActionEvent event) throws IOException {

            StageUtil editCar = new StageUtil("/fxml/editCarInfo.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());
            EditCarController editCarController = editCar.getLoader().getController();
            editCarController.setParentController(this);
        }

    @FXML
    void onScheduleRental(ActionEvent event) throws IOException {

        StageUtil addRental = new StageUtil("/fxml/makeRental.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());
        RentalController rentalController = (RentalController) addRental.getController();
        rentalController.setAgentViewController(parentController);
        rentalController.setCar(this.car);
    }

    @FXML
    void onDeleteClick(ActionEvent event) throws SQLException {

        if(car.getCar_currentlyRented()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Delete Error");
            alert.setHeaderText("Car Deletion Error");
            alert.setContentText("Car cannot be deleted since it is currently Rented.");
            alert.showAndWait();
        }

        else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Confirmation");
            alert.setHeaderText("Car Deletion");
            alert.setContentText("Are you sure you want to delete this car?");
            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

            if(result == ButtonType.OK) {

                System.out.println(SQLHandlerUtil.deleteCar(car.getCar_id()));
                parentController.getActiveAgent().setCars(SQLHandlerUtil.getAgentCars(parentController.getActiveAgent().getAgentID()));
                parentController.initializeTable();
            }
        }
    }

    @FXML
    void onReassignClick(ActionEvent event) throws IOException {

        StageUtil modalStage = new StageUtil("/fxml/reassignCar.fxml", (Stage)deleteButton.getScene().getWindow());
        ReassignCarController reassignController = (ReassignCarController) modalStage.getController();
        reassignController.setControllers(car, parentController);
    }

    void setData(Car car) {
        this.car = car;
        this.carColor.setText("Color: " + car.getCar_color());
        this.carID.setText("ID: " + car.getCar_id());
        this.carName.setText(car.getCar_make() + " " + car.getCar_model() + " " + car.getCar_year());
        this.carType.setText("Type: " + Account.getCarTypeList().get(car.getCar_type_id() - 1).getType());
        this.carSeats.setText("Passengers: " + Account.getCarTypeList().get(car.getCar_type_id() - 1).getPassengerCount());
    }

    void setParentController(ViewAgentsController controller) {
        this.parentController = controller;
    }
    ViewAgentsController getParentController() {return this.parentController;}
    Car getCar() {return this.car;}
}
