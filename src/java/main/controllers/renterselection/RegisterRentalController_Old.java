package main.controllers.renterselection;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.controllers.units.UnitCarController;
import main.controllers.views.DashboardMain;
import main.controllers.views.ViewCarController;
import main.objects.Car;
import main.objects.Rental;
import main.objects.Renter;
import main.util.DateUtil;
import main.util.SQLHandlerUtil;
import main.util.StageUtil;
import main.objects.Account;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterRentalController_Old implements Initializable {

    @FXML
    private JFXButton cancelButton;

    @FXML
    private Label carName;

    @FXML
    private Label emptyFieldWarning;

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
    private UnitCarController unitCarController;
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
        if(renterFirstName.getText().isEmpty() || renterLastname.getText().isEmpty() || startDatePicker.getValue() == null || endDatePicker.getValue() == null) {
            emptyFieldWarning.setVisible(true);
            return;
        }

        for(Rental rental : Account.getRentalsList()) {
            if(rental.getRentStart().isBefore(endDatePicker.getValue()) && rental.getRentEnd().isAfter(startDatePicker.getValue())) {
                emptyFieldWarning.setText("There is already a rental schedule between the start and end date");
                emptyFieldWarning.setVisible(true);
                return;
            }
        }

        if(endDatePicker.getValue().isBefore(startDatePicker.getValue())) {
            emptyFieldWarning.setText("End Date must be after Start Date");
            emptyFieldWarning.setVisible(true);
            return;
        }

        int totalDays = (int) ChronoUnit.DAYS.between(startDatePicker.getValue(), endDatePicker.getValue());

        Account.addRentals(SQLHandlerUtil.addRental(viewCarController.getActiveAgent().getAgentID(), renter.getRenterID(), car.getCar_id(), startDatePicker.getValue(), endDatePicker.getValue(), totalDays * car.getDailyRate()));

        viewCarController.getActiveAgent().setCars(SQLHandlerUtil.getAgentCars(viewCarController.getActiveAgent().getAgentID()));

        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }


    @FXML
    void selectRegisterClicked(ActionEvent event) throws IOException {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
        unitCarController.onScheduleRentalNew(event);
    }

    @FXML
    void selectRenterClicked(ActionEvent event) throws IOException {
        StageUtil addRenter = new StageUtil("/fxml/selectRenter.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());
        SelectRenterController selectRenterController = addRenter.getLoader().getController();
        selectRenterController.setParentController(this);
    }

    public void setData(Renter renter) {
        this.renter = renter;
        renterFirstName.setText(renter.getFirstName());
        renterLastname.setText(renter.getLastName());
        renterID.setText(String.valueOf(renter.getRenterID()));

    }

    public void setRenter(Renter renter) {
        this.renter = renter;
        setData(renter);
    }

    public void setAgentViewController(ViewCarController viewCarController) {this.viewCarController = viewCarController;}
    public void setUnitCarController(UnitCarController unitCarController) {this.unitCarController = unitCarController;}
    public void setCar(Car car) {
        this.car = car;
        this.carName.setText(car.getCar_make() + " " + car.getCar_model() + " " + car.getCar_year());
    }
}
