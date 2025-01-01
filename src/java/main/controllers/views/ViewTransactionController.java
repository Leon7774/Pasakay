package main.controllers.views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ViewTransactionController {

    @FXML private Label amountLabel, transactionLabel, dateLabel, rentalIDLabel;

    public void setData(int rental_id, String date, double amount, String transactionName) {
        rentalIDLabel.setText(String.valueOf(rental_id));
        dateLabel.setText(date);
        amountLabel.setText(String.valueOf(amount));
        transactionLabel.setText(transactionName);
    }

}