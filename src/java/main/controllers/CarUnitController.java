package main.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.objects.Account;
import main.objects.Car;

public class CarUnitController {

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
    private Label netIncome;

    @FXML
    private JFXButton scheduleRental;

    private AgentViewController parentController;

    @FXML
    void onEditClick(ActionEvent event) {

    }

    @FXML
    void onScheduleRental(ActionEvent event) {

    }

    void setData(Car car) {
        this.carColor.setText("Color: " + car.getCar_color());
        this.carID.setText("ID: " + car.getCar_id());
        this.carName.setText(car.getCar_make() + " " + car.getCar_model() + " " + car.getCar_year());
        this.carType.setText("Type: " + Account.getCarTypeList().get(car.getCar_type_id() - 1).getType());
        this.carSeats.setText("Passengers: " + Account.getCarTypeList().get(car.getCar_type_id() - 1).getPassengerCount());
    }

    void setParentController(AgentViewController controller) {
        this.parentController = controller;
    }

}
