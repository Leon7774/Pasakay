package main.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class RentalController implements Initializable {

    @FXML private TextField agePrompt, firstNamePrompt, lastNamePrompt, licenseNumberPrompt;
    @FXML private JFXButton cancelButton, scheduleButton;
    @FXML private TextField contactNumberPrompt;
    @FXML private Text emptyWarningLabel;
    @FXML private JFXComboBox<String> sexPrompt, statusPrompt;
    @FXML private DatePicker startDatePicker, endDatePicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String [] sexs = {"Male", "Female"};
        String [] status = {"Single", "Married", "Widowed", "Complicated"};

        sexPrompt.getItems().addAll(sexs);
        statusPrompt.getItems().addAll(status);
    }

    @FXML
    void onCancelClick(ActionEvent event) {


    }

    @FXML
    void onScheduleClick(ActionEvent event) {


    }
}
