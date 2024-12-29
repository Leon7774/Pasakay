package main.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.objects.Account;
import main.objects.Car;
import main.objects.Rentals;
import main.util.SQLHandlerUtil;

import java.io.IOException;
import java.sql.SQLException;

public class RentersDashboardController {

    @FXML private ImageView closeButton;
    @FXML private AnchorPane dashboardContent;
    @FXML private Text dashboardTitle;
    @FXML private ScrollPane scrollPane;
    @FXML private VBox vboxContent;

    @FXML
    void closeButtonOnActionEvent(MouseEvent event) {

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void dilightClose(MouseEvent event) {closeButton.setImage(new Image("/images/close-highlight.png"));}

    @FXML
    void highlightClose(MouseEvent event) {closeButton.setImage(new Image("/images/close.png"));}

//    void initializeTable() {
//
//        vboxContent.getChildren().clear();
//
//        for(Rentals rental : Account.getRentalsList()) {
//
//            try {
//
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/carView.fxml"));
//                HBox hbox = loader.load();
//                CarViewController carViewController = loader.getController();
//                Car car = SQLHandlerUtil.getOneCar(rental.getCarId());
//                carViewController.setData(car, rental);
//                carViewController.setParentController(this);
//                vboxContent.getChildren().add(hbox);
//            }
//
//            catch (IOException | SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
}
