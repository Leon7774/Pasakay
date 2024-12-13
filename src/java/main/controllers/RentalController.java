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
import main.objects.Renter;
import main.util.SQLHandlerUtil;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    private AgentViewController agentViewController;

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
    void onScheduleClick(ActionEvent event) throws SQLException {

        String firstName = firstNamePrompt.getText();
        String lastName = lastNamePrompt.getText();
        String status = statusPrompt.getValue();
        String sex = sexPrompt.getValue();
        int age = Integer.parseInt(agePrompt.getText());
        int contactNumber = Integer.parseInt(contactNumberPrompt.getText());
        int licenseNumber = Integer.parseInt(licenseNumberPrompt.getText());
        String endDate = endDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String startDate = startDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Renter renter = SQLHandlerUtil.addRenter(firstName, lastName, sex, status, age, contactNumber, licenseNumber);
//        SQLHandlerUtil.addRental(agentViewController.getActiveAgent().getAgentID(), renter.getRenterID(), startDate, endDate, )
    }

    public void setAgentViewController(AgentViewController agentViewController) {this.agentViewController = agentViewController;}
}
