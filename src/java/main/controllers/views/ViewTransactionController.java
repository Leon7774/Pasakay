package main.controllers.views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ViewTransactionController {

    @FXML private Label amountLabel, transactionLabel, dateLabel, rentalIDLabel, transactionIDLabel;

    public void setData(int transaction_id, int rental_id, String date, double amount, String transactionName) {
        rentalIDLabel.setText("Rental ID: " + String.valueOf(rental_id));
        dateLabel.setText("Date: " + date);
        amountLabel.setText("Amount: " + String.valueOf(amount));
        transactionLabel.setText("Transaction: " + transactionName);
        transactionIDLabel.setText("Transaction ID: " + String.valueOf(transaction_id));
    }

}