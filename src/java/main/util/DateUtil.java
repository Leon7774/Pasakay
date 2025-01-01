package main.util;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;
import main.objects.Account;
import main.objects.Car;
import main.objects.Rental;

import java.time.LocalDate;

public class DateUtil {
    public static Callback<DatePicker, DateCell> createDayCellFactory(LocalDate date, Car car) {
        return datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                // Disable past dates
                if (item.isBefore(date)) {
                    setDisable(true);
                    setStyle("-fx-background-color: #636363;"); // Optional styling
                }

                for(Rental r : Account.getRentalsList()) {
                    if(r.getCarId() == car.getCar_id() && ((r.getRentStart().isBefore(item) && r.getRentEnd().isAfter(item)) || item.isEqual(r.getRentStart()) || item.isEqual(r.getRentEnd()))) {
                        setDisable(true);
                        setStyle("-fx-background-color: #958aff;"); // Optional styling
                    }
                }
            }
        };
    }
}
