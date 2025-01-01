package main.controllers.renterselection;

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
import main.controllers.views.DashboardMain;
import main.controllers.views.ViewCarController;
import main.objects.Account;
import main.objects.Car;
import main.objects.Rental;
import main.objects.Renter;
import main.util.DateUtil;
import main.util.SQLHandlerUtil;
import main.util.StageUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class RegisterRentalController_New implements Initializable {

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

    private ViewCarController viewCarController;
    private Car car;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emptyFieldWarning.setVisible(false);

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
    void onScheduleClick(ActionEvent event) throws SQLException, IOException {

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

        int totalDays = (int) ChronoUnit.DAYS.between(startDatePicker.getValue(), endDatePicker.getValue()) + 1;

        StageUtil confirmRental = new StageUtil("/fxml/confirmDeposit.fxml", (Stage)((Node)event.getSource()).getScene().getWindow(), 1);
        ConfirmDepositController controller = confirmRental.getLoader().getController();
        controller.setDepositValue((totalDays * car.getDailyRate())*0.2);
        confirmRental.showAndWait();

        if(!controller.isConfirmed()) {
            return;
        }

        Renter renter = SQLHandlerUtil.addRenter(firstName, lastName, status, sex, Integer.parseInt(age), Integer.parseInt(contactNumber), Integer.parseInt(licenseNumber));
        Rental newRental = SQLHandlerUtil.addRental(viewCarController.getActiveAgent().getAgentID(), renter.getRenterID(), car.getCar_id(), startDatePicker.getValue(), endDatePicker.getValue(), totalDays * car.getDailyRate());

        Account.addRentals(newRental);
        SQLHandlerUtil.addTransaction(newRental.getId(), "Deposit", newRental.getTotalCost() * .2, DashboardMain.getCurrentDate().toString());

        viewCarController.getActiveAgent().setCars(SQLHandlerUtil.getAgentCars(viewCarController.getActiveAgent().getAgentID()));

        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }
    public void setAgentViewController(ViewCarController viewCarController) {this.viewCarController = viewCarController;}
    public void setCar(Car car) {
        this.car = car;
        this.carName.setText(car.getCar_make() + " " + car.getCar_model() + " " + car.getCar_year());
        startDatePicker.setDayCellFactory(DateUtil.createDayCellFactory(DashboardMain.getCurrentDate(), this.car));
        endDatePicker.setDayCellFactory(DateUtil.createDayCellFactory(DashboardMain.getCurrentDate(), this.car));
    }
}
