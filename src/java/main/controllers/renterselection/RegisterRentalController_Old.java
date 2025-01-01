package main.controllers.renterselection;

import com.jfoenix.controls.JFXButton;
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
import main.objects.Car;
import main.objects.Renter;
import main.util.DateUtil;
import main.util.StageUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class RegisterRentalController_Old implements Initializable {

    @FXML
    private JFXButton cancelButton;

    @FXML
    private Label carName;

    @FXML
    private Label emptyFieldWarning;

    @FXML
    private Text emptyWarningLabel;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TextField renterFirstName;

    @FXML
    private TextField renterID;

    @FXML
    private TextField renterLastname;

    @FXML
    private JFXButton scheduleButton;

    @FXML
    private JFXButton selectRenterButton;

    @FXML
    private DatePicker startDatePicker;

    private ViewCarController viewCarController;
    private Car car;
    private Renter renter;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emptyFieldWarning.setVisible(false);
        startDatePicker.setDayCellFactory(DateUtil.createDayCellFactory(DashboardMain.getCurrentDate()));
        endDatePicker.setDayCellFactory(DateUtil.createDayCellFactory(DashboardMain.getCurrentDate().plus(1, ChronoUnit.DAYS)));
    }

    @FXML
    void onCancelClick(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }


    @FXML
    void onScheduleClick(ActionEvent event) throws SQLException {
        /*
        if(firstName.isEmpty() || lastName.isEmpty() || status == null || sex == null || age.isEmpty() || contactNumber.isEmpty() || licenseNumber.isEmpty()) {
            emptyFieldWarning.setVisible(true);
            return;
        }

        int totalDays = (int) ChronoUnit.DAYS.between(startDatePicker.getValue(), endDatePicker.getValue());

        Renter renter = SQLHandlerUtil.addRenter(firstName, lastName, status, sex, Integer.parseInt(age), Integer.parseInt(contactNumber), Integer.parseInt(licenseNumber));
        Account.addRentals(SQLHandlerUtil.addRental(viewCarController.getActiveAgent().getAgentID(), renter.getRenterID(), car.getCar_id(), startDatePicker.getValue(), endDatePicker.getValue(), totalDays * car.getDailyRate()));

        viewCarController.getActiveAgent().setCars(SQLHandlerUtil.getAgentCars(viewCarController.getActiveAgent().getAgentID()));

        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();

         */
    }


    @FXML
    void selectRegisterClicked(ActionEvent event) {

    }

    @FXML
    void selectRenterClicked(ActionEvent event) throws IOException {
        StageUtil addRenter = new StageUtil("/fxml/selectRenter.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());
        SelectRenterController selectRenterController = addRenter.getLoader().getController();
    }

    public void setAgentViewController(ViewCarController viewCarController) {this.viewCarController = viewCarController;}
    public void setCar(Car car) {this.car = car;}
}
