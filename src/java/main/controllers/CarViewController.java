package main.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.objects.Car;

public class CarViewController {

    @FXML private Label agentIDLabel, carColorLabel, carIDLabel, carName, isRentedLabel, netIncome, noSeatsLabel,
                        rentEndLabel, rentStartLabel;
    @FXML private JFXButton deleteButton, editButton;

    @FXML
    void onEditClick(ActionEvent event) {


    }

    public void setData(Car car) {


    }

}

