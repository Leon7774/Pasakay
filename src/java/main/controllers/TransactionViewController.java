package main.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TransactionViewController {

    @FXML private Label amountLabel, transactionLabel, dateLabel, rentalIDLabel;

    void setData(int rental_id, String date, double amount, String transactionName) {

        rentalIDLabel.setText(String.valueOf(rental_id));
        dateLabel.setText(date);
        amountLabel.setText(String.valueOf(amount));
        transactionLabel.setText(transactionName);
    }
}