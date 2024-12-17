package main.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class RentalController implements Initializable {

    @FXML
    private TextField agePrompt;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private Label carName;

    @FXML
    private TextField contactNumberPrompt;

    @FXML
    private Label emptyFieldWarning;

    @FXML
    private Text emptyWarningLabel;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TextField firstNamePrompt;

    @FXML
    private TextField lastNamePrompt;

    @FXML
    private TextField licenseNumberPrompt;

    @FXML
    private JFXButton scheduleButton;

    @FXML
    private JFXComboBox<String> sexPrompt;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private JFXComboBox<String> statusPrompt;

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
