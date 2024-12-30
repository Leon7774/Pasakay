package main.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.objects.Account;
import main.objects.Car;
import main.objects.Rentals;
import main.util.SQLHandlerUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewRentalsController implements Initializable {

    @FXML private ImageView closeButton;
    @FXML private ScrollPane scrollPane;
    @FXML private VBox vboxContent;
    @FXML private Label notifCounter;
    @FXML private AnchorPane notifIcon;

    private ViewAgentsController viewAgentsController;

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

    void initializeTable() {

        vboxContent.getChildren().clear();

        for(Rentals rental : Account.getRentalsList()) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitRental.fxml"));
                HBox hbox = loader.load();
                UnitRentalController unitRentalController = loader.getController();
                Car car = SQLHandlerUtil.getOneCar(rental.getCarId());
                unitRentalController.setData(car, rental);
                unitRentalController.setParentController(this);
                vboxContent.getChildren().add(hbox);
            }

            catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}