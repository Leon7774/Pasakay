package main.controllers.units;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.controllers.prompts.ConfirmCarReturnController;
import main.controllers.prompts.ConfirmPaymentController;
import main.controllers.prompts.MissingCarController;
import main.controllers.views.DashboardMain;
import main.controllers.views.ViewRentalsController;
import main.objects.Account;
import main.objects.Car;
import main.objects.Rental;
import main.util.SQLHandlerUtil;
import main.util.StageUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;

public class UnitRentalController {

    @FXML private Label agentIDLabel, renterIDLabel, carColorLabel, carIDLabel, carName, isRentedLabel, netIncomeLabel, noSeatsLabel,
                        rentEndLabel, rentStartLabel, rentalID;
    @FXML private JFXButton deleteButton, editButton;
    @FXML private Label totalRentDays;
    @FXML private ImageView notifIcon;
    @FXML private AnchorPane notifArea;
    @FXML private JFXButton pendingButton;
    @FXML private HBox container;

    private ViewRentalsController viewRentalsController;
    private Rental currentRental;
    private boolean isPending = false;
    private boolean finished = false;

    // condition for the Pending variable
    // -2 = in rent, -1 = pending, 0 = completed, 1 = day of payment/pickup, 2 = day of return, 3 = not returned
    private int condition;

    @FXML
    void initialize() {
        notifArea.setVisible(false);
    }

    @FXML
    void onDeleteClick(ActionEvent event) throws SQLException {

        System.out.println(currentRental.getId());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("Rental Deletion");
        alert.setContentText("Are you sure you want to delete this rental?");
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        if(result == ButtonType.OK) {

            System.out.println(SQLHandlerUtil.deleteRental(currentRental.getId()));
            viewRentalsController.initializeTable();
        }
    }

    public boolean isPending() {return isPending;}

    public void setData(Car car, Rental rental) throws SQLException, IOException {

        this.carName.setText(car.getCar_model());
        this.rentalID.setText("Rental ID: " + String.valueOf(rental.getId()));
        this.carIDLabel.setText("Car ID: " + String.valueOf(car.getCar_id()));
        this.carColorLabel.setText("Color: " + car.getCar_color());
        this.noSeatsLabel.setText("Number of Seats: " + String.valueOf(SQLHandlerUtil.getCarType(car.getCar_type_id()).getPassengerCount()));
        this.isRentedLabel.setText("Currently Rented: " + (car.getCar_currentlyRented() ? "Yes" : "No"));
        this.agentIDLabel.setText("Agent ID: " + String.valueOf(rental.getAgentId()));

        this.renterIDLabel.setText("Renter ID: " + String.valueOf(rental.getRenterID()));
        this.rentStartLabel.setText("Rent Start Date: " + String.valueOf(rental.getRentStart()));
        this.rentEndLabel.setText("Rent End Date: " + String.valueOf(rental.getRentEnd()));
        this.netIncomeLabel.setText("Expected Income: $" + String.valueOf(rental.getTotalCost()));
        this.totalRentDays.setText("Total Rental Days: " + String.valueOf( ChronoUnit.DAYS.between(rental.getRentStart(), rental.getRentEnd()) + 1));

        if(rental.getRentEnd().isBefore(DashboardMain.getCurrentDate()) && rental.isReturned()) {
            finished = true;
            container.setStyle("-fx-background-color: #cccccc; -fx-background-radius: 10;");
        }

        Tooltip tooltip = new Tooltip(Account.findAgentByID(rental.getAgentId()).getFirstname() + " " + Account.findAgentByID(rental.getAgentId()).getLastname());

        agentIDLabel.setTooltip(tooltip);

        if (!rental.isReturned()) {
            if (DashboardMain.getCurrentDate().equals(rental.getRentStart()) && !rental.isFullyPaid()) {
                isPending = true;
                notifArea.setVisible(true);
                viewRentalsController.updateCounter();
                condition = 1;

            }else if (DashboardMain.getCurrentDate().equals(rental.getRentEnd()) && !rental.isReturned()) {
                isPending = true;
                notifArea.setVisible(true);
                viewRentalsController.updateCounter();
                condition = 2;
            }else if (DashboardMain.getCurrentDate().isAfter(rental.getRentEnd()) && !rental.isReturned()) {
                isPending = true;
                notifArea.setVisible(true);
                viewRentalsController.updateCounter();
                condition = 3;
            }
        }

        currentRental = rental;
    }

    public void setParentController(ViewRentalsController viewRentalsController){
        this.viewRentalsController = viewRentalsController;
    }

    public Rental getCurrentRental() {
        return currentRental;
    }

    public boolean isFinished() {
        return finished;
    }

    @FXML
    void onPendingClicked(Event event) throws IOException, SQLException {
        Car car = SQLHandlerUtil.getOneCar(currentRental.getCarId());
        switch (condition) {
            case 1:
                StageUtil payment = new StageUtil("/fxml/confirmPayment.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());
                ConfirmPaymentController paymentController = (ConfirmPaymentController) payment.getController();
                paymentController.setRental(this.getCurrentRental());
                paymentController.setViewRentalsController(viewRentalsController);
                paymentController.setDeposit((double) currentRental.getTotalCost() * .8);
                break;
            case 2:
                StageUtil collection = new StageUtil("/fxml/confirmCarReturn.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());
                ConfirmCarReturnController carReturnController = (ConfirmCarReturnController) collection .getController();
                carReturnController.setParentController(this);
                carReturnController.setRental(this.getCurrentRental());
                carReturnController.setViewRentalsController(viewRentalsController);
                carReturnController.setCarName(car.getCar_make() + " " + car.getCar_model() + " " + car.getCar_year());
                break;
            case 3:
                StageUtil missing = new StageUtil("/fxml/carMissing.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());
                MissingCarController missingController = (MissingCarController) missing.getController();
                missingController.setRental(this.currentRental);
                missingController.setOverdueAmount(car.getDailyRate() * (ChronoUnit.DAYS.between(currentRental.getRentEnd(), DashboardMain.getCurrentDate()) + 1) * 2);
                missingController.setOverdueDays((int) ChronoUnit.DAYS.between(currentRental.getRentEnd(), DashboardMain.getCurrentDate()));
                break;
        }
    }

}

