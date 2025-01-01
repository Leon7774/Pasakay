package main.controllers.views;

import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import main.objects.Rental;
import main.util.SQLHandlerUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ViewRentalController implements Initializable {

    @FXML private Label agentIDLabel, renterIDLabel, netIncomeLabel,
                        rentEndLabel, rentStartLabel, rentalIDLabel,
                        carIDLabel;

    @FXML private JFXToggleButton depositedToggle, fullyPaidToggle;

    private ViewRentalsController rentalDashboardController;
    private Rental currentRental;
    private boolean deposited = false, fullyPaid = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        fullyPaidToggle.setOnAction(event -> {

            if(fullyPaid) return;

            fullyPaid = true;

            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationDialog.setTitle("Confirmation");
            confirmationDialog.setHeaderText("Are you sure?");
            confirmationDialog.setContentText("Did Renter " + currentRental.getRenterID() + " already pay in full?");

            Optional<ButtonType> result = confirmationDialog.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                fullyPaidToggle.setDisable(true);
                try {
                    SQLHandlerUtil.setFullyPaid(currentRental.getId(), true);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                fullyPaidToggle.setSelected(!fullyPaidToggle.isSelected()); // Revert to the previous value
            }

            fullyPaid = false; // Reset the guard variable
        });

        depositedToggle.setOnAction(event -> {

           if(deposited) return;

           deposited = true;

            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationDialog.setTitle("Confirmation");
            confirmationDialog.setHeaderText("Are you sure?");
            confirmationDialog.setContentText("Did Renter " + currentRental.getRenterID() + " already pay in full?");

            Optional<ButtonType> result = confirmationDialog.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                depositedToggle.setDisable(true);
                try {
                    SQLHandlerUtil.setFullyPaid(currentRental.getId(), true);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                depositedToggle.setSelected(!depositedToggle.isSelected()); // Revert to the previous value
            }

            deposited = false; // Reset the guard variable
        });
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

            System.out.println(SQLHandlerUtil.deleteRental(currentRental.getId(), currentRental.getRenterID()));
            rentalDashboardController.initializeTable();
        }
    }

    void setData(Rental rental) throws SQLException {

        this.agentIDLabel.setText("Agent ID: " + String.valueOf(rental.getAgentId()));
        this.renterIDLabel.setText("Renter ID: " + String.valueOf(rental.getRenterID()));
        this.rentStartLabel.setText("Rent Start Date: " + String.valueOf(rental.getRentStart()));
        this.rentEndLabel.setText("Rent End Date: " + String.valueOf(rental.getRentEnd()));
        this.netIncomeLabel.setText("Expected Income: $" + String.valueOf(rental.getTotalCost()));
        this.rentalIDLabel.setText("Rental ID: " + String.valueOf(rental.getId()));
        this.carIDLabel.setText("Car ID: " + String.valueOf(rental.getCarId()));
        currentRental = rental;

        if(currentRental.isDeposited()) {

            depositedToggle.setSelected(true);
            depositedToggle.setDisable(true);
        }

        if(currentRental.isFullyPaid()) {

            fullyPaidToggle.setSelected(true);
            fullyPaidToggle.setDisable(true);
        }
    }

    void setParentController(ViewRentalsController rentalsViewController) {
        this.rentalDashboardController = rentalsViewController;
    }

}

