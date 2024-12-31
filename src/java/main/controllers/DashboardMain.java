package main.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.objects.Account;
import main.objects.Rental;
import main.util.StageUtil;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardMain implements Initializable {

    @FXML private AnchorPane dashboardContent;
    @FXML private Label welcomeLabel;
    @FXML private ImageView closeButton;
    @FXML private DatePicker dateInput;

    private static LocalDate currentDate = LocalDate.now();

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

        dateInput.setPromptText(currentDate.toString());

        for (Rental rental : Account.getRentalsList()) {
            System.out.println(rental);
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/viewAgents.fxml"));
        BorderPane rentalsPane = loader.load();

        dashboardContent.getChildren().clear();
        dashboardContent.getChildren().setAll(rentalsPane);
    }

    @FXML
    void onRentalsClicked (ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/viewRentals.fxml"));
        BorderPane rentalsPane = loader.load();

        dashboardContent.getChildren().clear();
        dashboardContent.getChildren().setAll(rentalsPane);
    }

    @FXML
    void onRentersClicked (ActionEvent event) throws IOException {


    }

    @FXML
    void onInactiveCarsClick (ActionEvent event) throws IOException {


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

    @FXML
    void onDateChange(ActionEvent event) throws IOException {
        dashboardContent.getChildren().clear();
        System.out.println(dateInput.getValue());
        this.currentDate = dateInput.getValue();
        onAgentTabClick(new ActionEvent());
    }

    public static void setCurrentDate(LocalDate date) {
        currentDate = date;
    }

    public static LocalDate getCurrentDate() {
        return currentDate;
    }
}

