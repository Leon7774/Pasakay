package main.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.objects.Account;
import main.objects.Car;
import main.objects.Rentals;
import main.util.SQLHandlerUtil;
import main.util.SceneUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RentalsViewController implements Initializable {

    @FXML private JFXButton agentsTab;
    @FXML private ImageView closeButton;
    @FXML private AnchorPane dashboardContent;
    @FXML private Text dashboardTitle;
    @FXML private JFXButton logoutButton;
    @FXML private ScrollPane scrollPane;
    @FXML private VBox vboxContent;
    @FXML private Label welcomeLabel;

    private AgentViewController agentViewController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        welcomeLabel.setText("Welcome, " + Account.getFirstName());
        scrollPane.setStyle("-fx-background: white;");

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

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

    @FXML
    void onAgentTabClick(ActionEvent event) throws IOException {

        new SceneUtil("/fxml/dashboard.fxml", (Stage)closeButton.getScene().getWindow());
    }

    @FXML
    void onLogoutTabClick(ActionEvent event) {

    }

    void initializeTable() {

        vboxContent.getChildren().clear();

        for(Rentals rental : Account.getRentalsList()) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/carView.fxml"));
                HBox hbox = loader.load();
                CarViewController carViewController = loader.getController();
                Car car = SQLHandlerUtil.getOneCar(rental.getCarId());
                carViewController.setData(car, rental);
                carViewController.setParentController(this);
                vboxContent.getChildren().add(hbox);
            }

            catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}