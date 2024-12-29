import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.DateCell;

import java.time.LocalDate;

public class DatePickerRestriction extends Application {
    @Override
    public void start(Stage stage) {
        // Create a DatePicker
        DatePicker datePicker = new DatePicker();

        // Restrict past dates
        datePicker.setDayCellFactory(createDayCellFactory());

        // Add a tooltip
        datePicker.setTooltip(new Tooltip("Pick a date from today onwards"));

        // Layout
        VBox root = new VBox(10, datePicker);
        root.setStyle("-fx-padding: 20;");

        // Scene and Stage
        Scene scene = new Scene(root, 300, 200);
        stage.setTitle("DatePicker Restriction");
        stage.setScene(scene);
        stage.show();
    }

    private Callback<DatePicker, DateCell> createDayCellFactory() {
        return datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                // Disable past dates
                if (item.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffcccc;"); // Optional styling
                }
            }
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}
