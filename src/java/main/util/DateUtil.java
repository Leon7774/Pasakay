package main.util;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import java.time.LocalDate;

public class DateUtil {
    public static Callback<DatePicker, DateCell> createDayCellFactory(LocalDate date) {
        return datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                // Disable past dates
                if (item.isBefore(date)) {
                    setDisable(true);
                    setStyle("-fx-background-color: #636363;"); // Optional styling
                }
            }
        };
    }
}
