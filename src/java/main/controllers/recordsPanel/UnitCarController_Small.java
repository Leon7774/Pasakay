package main.controllers.recordsPanel;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.objects.Account;
import main.objects.Car;
import main.objects.Rental;

import java.time.temporal.ChronoUnit;

public class UnitCarController_Small {

    @FXML
    private Label carColor;

    @FXML
    private Label carID;

    @FXML
    private Label carName;

    @FXML
    private Label carType;

    @FXML
    private JFXButton chooseButton;

    private Car car;
    private SelectCarController parentController;

    @FXML
    void onChoose(ActionEvent event) {
        parentController.setCar(car);
        ((Stage)chooseButton.getScene().getWindow()).close();
    }

    void setParentController(SelectCarController controller) {
        this.parentController = controller;
    }

    void setData(Car car) {
        this.car = car;
        this.carColor.setText("Color: " + car.getCar_color());
        this.carID.setText("ID: " + car.getCar_id());
        this.carName.setText(car.getCar_make() + " " + car.getCar_model() + " " + car.getCar_year());
        this.carType.setText("Type: " + Account.getCarTypeList().get(car.getCar_type_id() - 1).getType());
    }

    public int getRentalDays() {
        int totalRentalDays = 0;
        for(Rental rental : Account.getRentalsList()) {
            if(rental.getCarId() == car.getCar_id()) {
                totalRentalDays += ChronoUnit.DAYS.between(rental.getRentStart(), rental.getRentEnd()) + 1;
            }
        }

        return totalRentalDays;
    }


}
