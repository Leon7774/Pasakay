package main.controllers.prompts;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.controllers.views.DashboardMain;
import main.objects.Account;
import main.objects.Rental;
import main.util.SQLHandlerUtil;

import java.sql.SQLException;

public class MissingCarController {

    @FXML
    private JFXButton cancelButton;

    @FXML
    private Label carName;

    @FXML
    private Label carName1;

    @FXML
    private Label carName2;

    @FXML
    private JFXButton confirmButton;

    @FXML
    private Label overdueAmount;

    @FXML
    private Label overdueDays;

    private Rental rental;

    @FXML
    void onCancelClick(ActionEvent event) {
        ((Stage) cancelButton.getScene().getWindow()).close();
    }

    @FXML
    void onConfirmClick(ActionEvent event) throws SQLException {
        rental.setReturned(true);
        SQLHandlerUtil.setCarReturned(rental.getId(), true);
        SQLHandlerUtil.addTransaction(rental.getId(), "Overdue Payment", rental.getTotalCost() * 0.8, DashboardMain.getCurrentDate().toString());
        SQLHandlerUtil.findUser(Account.getUserName());
        ((Stage) cancelButton.getScene().getWindow()).close();
    }

    public void setOverdueDays(int days) {
        overdueDays.setText(String.valueOf(days) + " days");
    }

    public void setOverdueAmount(double amount) {
        overdueAmount.setText("$"+String.valueOf(amount));
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

}
