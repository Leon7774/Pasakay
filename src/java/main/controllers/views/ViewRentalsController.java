package main.controllers.views;

import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.controllers.units.UnitRentalController;
import main.controllers.units.UnitRenterController_Select;
import main.objects.Account;
import main.objects.Car;
import main.objects.Rental;
import main.objects.Renter;
import main.util.SQLHandlerUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ViewRentalsController implements Initializable {

    @FXML private ImageView closeButton;
    @FXML private ScrollPane scrollPane;
    @FXML private VBox vboxContent;
    @FXML private Label notifCounter;
    @FXML private AnchorPane notifIcon;
    @FXML private JFXToggleButton showOldRentals;
    @FXML private JFXToggleButton showPriorityRentals;

    int counter = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Makes sure the scrollpane is invisible, by default the background is gray
        scrollPane.setStyle("-fx-background: white;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        notifIcon.setVisible(false);

        initializeTable();
    }

    @FXML
    void closeButtonOnActionEvent(MouseEvent event) {

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void dilightClose(MouseEvent event) {
        closeButton.setImage(new Image("/images/close-highlight.png"));
    }

    @FXML
    void highlightClose(MouseEvent event) {
        closeButton.setImage(new Image("/images/close.png"));
    }

    public void initializeTable() {
        counter = 0;
        vboxContent.getChildren().clear();

        for(Rental rental : Account.getRentalsList()) {

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitRental.fxml"));
                HBox hbox = loader.load();
                UnitRentalController unitRentalController = loader.getController();
                Car car = SQLHandlerUtil.getOneCar(rental.getCarId());
                unitRentalController.setParentController(this);
                unitRentalController.setData(car, rental);


                if (showPriorityRentals.isSelected()) {
                    if (unitRentalController.isPending()) {
                        vboxContent.getChildren().add(hbox);
                    }
                }
                else if (!showOldRentals.isSelected()) {
                    if (rental.getRentEnd().isAfter(DashboardMain.getCurrentDate()) || rental.getRentEnd().isEqual(DashboardMain.getCurrentDate())) {
                        vboxContent.getChildren().add(hbox);
                    }
                }
                else {
                    vboxContent.getChildren().add(hbox);
                }
            }

            catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void selectAction(ActionEvent event) {
        vboxContent.getChildren().clear();
        initializeTable();
    }


    @FXML
    void priorityClick(ActionEvent event) {
        vboxContent.getChildren().clear();
        initializeTable();
    }



    public void updateCounter() {
        counter++;
        notifCounter.setText(String.valueOf(counter));
        if (counter > 0) {
            notifIcon.setVisible(true);
        } else {
            notifIcon.setVisible(false);
        }
    }

    @FXML
    void search(KeyEvent event) throws SQLException {
        String keyword = ((TextField)event.getSource()).getText().toLowerCase();
        vboxContent.getChildren().clear();

        for (Rental rental : Account.getRentalsList()) {
            if (keyword.startsWith("id=")) {
                try {
                    // Grabs the renter id
                    int id = Integer.parseInt(keyword.substring(3));
                    if (rental.getId() == id) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitRental.fxml"));
                            // Makes a horizontal box for every agent
                            HBox hbox = loader.load();
                            // Grabs the controller of each agent hbox made -- This allows us to edit each hbox
                            UnitRentalController unitRentalController = loader.getController();
                            Car car = SQLHandlerUtil.getOneCar(rental.getCarId());
                            unitRentalController.setParentController(this);
                            unitRentalController.setData(car, rental);
                            // Passes the dashboard controller to each hbox, so that when a component is accessed from the hbox, the dashboard will be editable
                            vboxContent.getChildren().add(hbox);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } catch (NumberFormatException ignored) {}
            } else if (SQLHandlerUtil.getOneCar(rental.getCarId()).getCar_make().toLowerCase().startsWith(keyword) || SQLHandlerUtil.getOneCar(rental.getCarId()).getCar_model().toLowerCase().startsWith(keyword)) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitRental.fxml"));
                    // Makes a horizontal box for every agent
                    HBox hbox = loader.load();
                    // Grabs the controller of each agent hbox made -- This allows us to edit each hbox
                    UnitRentalController unitRentalController = loader.getController();
                    Car car = SQLHandlerUtil.getOneCar(rental.getCarId());
                    unitRentalController.setParentController(this);
                    unitRentalController.setData(car, rental);
                    // Passes the dashboard controller to each hbox, so that when a component is accessed from the hbox, the dashboard will be editable
                    vboxContent.getChildren().add(hbox);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}