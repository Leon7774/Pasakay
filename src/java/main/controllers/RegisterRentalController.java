package main.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.objects.Account;
import main.objects.Car;
import main.objects.Renter;
import main.util.DateUtil;
import main.util.SQLHandlerUtil;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class RegisterRentalController implements Initializable {

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

    private ViewAgentsController viewAgentsController;
    private Car car;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emptyFieldWarning.setVisible(false);
        startDatePicker.setDayCellFactory(DateUtil.createDayCellFactory(DashboardMain.getCurrentDate()));
        endDatePicker.setDayCellFactory(DateUtil.createDayCellFactory(DashboardMain.getCurrentDate().plus(1, ChronoUnit.DAYS)));

        String [] sexs = {"Male", "Female", "Other"};
        String [] status = {"Single", "Married", "Widowed", "Complicated"};

        sexPrompt.getItems().addAll(sexs);
        statusPrompt.getItems().addAll(status);
    }

    @FXML
    void onCancelClick(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    void onScheduleClick(ActionEvent event) throws SQLException {

        String firstName = firstNamePrompt.getText();
        String lastName = lastNamePrompt.getText();
        String status = statusPrompt.getValue();
        String sex = sexPrompt.getValue();
        String age = agePrompt.getText();
        String contactNumber = contactNumberPrompt.getText().trim();
        String licenseNumber = licenseNumberPrompt.getText().trim();

        if(firstName.isEmpty() || lastName.isEmpty() || status == null || sex == null || age.isEmpty() || contactNumber.isEmpty() || licenseNumber.isEmpty()) {
            emptyFieldWarning.setVisible(true);
            return;
        }

        int totalDays = (int) ChronoUnit.DAYS.between(startDatePicker.getValue(), endDatePicker.getValue());

        Renter renter = SQLHandlerUtil.addRenter(firstName, lastName, status, sex, Integer.parseInt(age), Integer.parseInt(contactNumber), Integer.parseInt(licenseNumber));
        Account.addRentals(SQLHandlerUtil.addRental(viewAgentsController.getActiveAgent().getAgentID(), renter.getRenterID(), car.getCar_id(), startDatePicker.getValue(), endDatePicker.getValue(), totalDays * car.getDailyRate()));

        viewAgentsController.getActiveAgent().setCars(SQLHandlerUtil.getAgentCars(viewAgentsController.getActiveAgent().getAgentID()));

        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }
    public void setAgentViewController(ViewAgentsController viewAgentsController) {this.viewAgentsController = viewAgentsController;}
    public void setCar(Car car) {this.car = car;}
}
