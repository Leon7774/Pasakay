package main.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.objects.Account;
import main.objects.Agent;
import main.objects.Car;
import main.util.FXMLLoaderUtil;
import main.util.SceneUtil;
import main.util.StageUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private AnchorPane dashboardContent;
    @FXML
    private Label welcomeLabel;
    @FXML
    private ImageView closeButton;
    private static boolean opened = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO FIX LABEL
        setWelcomeLabel();
        welcomeLabel.setText("Welcome, " + Account.getFirstName());
        try {
            onAgentTabClick(new ActionEvent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setWelcomeLabel() {
        if (!opened) {
            welcomeLabel.setText("Welcome, " + Account.getFirstName());
            opened = true;
        }
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/agentsDashboard.fxml"));
        BorderPane rentalsPane = loader.load();

        dashboardContent.getChildren().clear();
        dashboardContent.getChildren().setAll(rentalsPane);
    }

    @FXML
    void onRentalsClicked (ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/rentalsDashboard.fxml"));
        BorderPane rentalsPane = loader.load();

        dashboardContent.getChildren().clear();
        dashboardContent.getChildren().setAll(rentalsPane);
    }

    @FXML
    public void closeButtonOnActionEvent(MouseEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onLogoutTabClick(ActionEvent event) {
        // Close the current stage
        Stage currentStage = (Stage) dashboardContent.getScene().getWindow();
        currentStage.close();

        // Launch a new login stage
        Platform.runLater(() -> {
            try {
                StageUtil util = new StageUtil("/fxml/login.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

