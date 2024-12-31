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

public class UnitInactiveCarController implements BaseCarController{

    @FXML private JFXButton assignButton, deleteButton, editButton;
    @FXML private Label carColor, carID, carName, carSeats, carType, dailyRent;

    private Car car;
    private ViewInactiveCarsController parentController;

    @FXML
    void onAssignClick(ActionEvent event) throws IOException {

        StageUtil modalStage = new StageUtil("/fxml/reassignCar.fxml", (Stage)deleteButton.getScene().getWindow());
        ReassignCarController reassignController = (ReassignCarController) modalStage.getController();
        reassignController.setControllers(car, parentController);
    }

    @FXML
    void onDeleteClick(ActionEvent event) throws SQLException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Car Deletion");
        alert.setContentText("Are you sure you want to delete this car?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if(result == ButtonType.OK) {

            System.out.println(SQLHandlerUtil.deleteCar(car.getCar_id()));
            parentController.initializeTable();
        }
    }

    @FXML
    void onEditClick(ActionEvent event) throws IOException {

        StageUtil editCar = new StageUtil("/fxml/editCarInfo.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());
        EditCarController editCarController = editCar.getLoader().getController();
        editCarController.setParentController(this);
    }

    void setData(Car car) {

        this.car = car;
        this.carColor.setText("Color: " + car.getCar_color());
        this.carID.setText("ID: " + car.getCar_id());
        this.carName.setText(car.getCar_make() + " " + car.getCar_model() + " " + car.getCar_year());
        this.carType.setText("Type: " + Account.getCarTypeList().get(car.getCar_type_id() - 1).getType());
        this.carSeats.setText("Passengers: " + Account.getCarTypeList().get(car.getCar_type_id() - 1).getPassengerCount());
        this.dailyRent.setText("Daily Rate: $" + car.getDailyRate());
    }

    void setParentController(ViewInactiveCarsController parentController) {

        this.parentController = parentController;
    }

    @Override
    public Car getCar() {
        return car;
    }

    @Override
    public Object getParentController() {
        return this.parentController;
    }
}
