package main.controllers.prompts;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import main.controllers.recordsPanel.ViewRecordsController;
import main.controllers.views.ViewTransactionsController;

import java.sql.SQLException;
import java.time.LocalDate;

public class SelectTransactionDatesController {

    @FXML
    private JFXButton applyButton;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private DatePicker startDatePicker;

    private ViewTransactionsController parentController;

    @FXML
    void onApplyClick(ActionEvent event) throws SQLException {

        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        if(startDate == null || endDate == null) {

            new Alert(Alert.AlertType.ERROR, "Please select the start and end dates",  ButtonType.OK).show();
            return;
        }

        else if(startDate.isAfter(endDate)) {

            new Alert(Alert.AlertType.ERROR, "Start date cannot be ahead of end date",  ButtonType.OK).show();
            return;
        }

        else if(startDate.isEqual(endDate)) {

            new Alert(Alert.AlertType.ERROR, "Start date cannot be equal to end date.",  ButtonType.OK).show();
            return;
        }

        startDate = startDate.minusDays(1);
        endDate = endDate.plusDays(1);

        parentController.setDates(new LocalDate[]{startDate, endDate});
        parentController.initializeTable();
        parentController.setClearButton();
        ((Stage)applyButton.getScene().getWindow()).close();
    }

    @FXML
    void onCancelClick(ActionEvent event) {

        ((Stage)applyButton.getScene().getWindow()).close();
    }

    public void setParentController(ViewTransactionsController parentController) {this.parentController = parentController;}
}
