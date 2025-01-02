package main.controllers.views;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import main.objects.Account;
import main.objects.Rental;
import main.util.SQLHandlerUtil;
import main.util.StageUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardMain implements Initializable {

    @FXML
    private JFXButton agentsButton;

    @FXML
    private ImageView closeButton;

    @FXML
    private AnchorPane dashboardContent, profilePane;

    @FXML
    private DatePicker dateInput;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXButton rentalsButton;

    @FXML
    private JFXButton rentersButton;

    @FXML
    private JFXButton reportButton;

    @FXML
    private JFXButton transactionsButton;

    @FXML
    private Label welcomeLabel;

    @FXML
    private JFXButton inactiveCarsButton;

    @FXML
    private ImageView profileView;

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
    void onClickProfile(MouseEvent event) {

        loadPane("/fxml/viewUserInformation.fxml");

    }

    @FXML
    void onAgentTabClick(ActionEvent event) throws IOException {
        changeButtonsColor(agentsButton);
        loadPane("/fxml/viewAgents.fxml");
    }

    @FXML
    void onRentalsClicked (ActionEvent event) throws IOException {
        changeButtonsColor(rentalsButton);
        loadPane("/fxml/viewRentals.fxml");
    }

    @FXML
    void onTransactionsClicked (ActionEvent event) throws IOException {
        changeButtonsColor(transactionsButton);
        loadPane("/fxml/viewTransaction.fxml");
    }

    @FXML
    void onRentersClicked (ActionEvent event) throws IOException {
        changeButtonsColor(rentersButton);
        loadPane("/fxml/viewRenter.fxml");
    }

    @FXML
    void onReportClicked(ActionEvent event) {
        changeButtonsColor(reportButton);
        //loadPane("/fxml/viewRenter.fxml");
    }

    // Loads the given fxml into the dashboard
    private void loadPane(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        BorderPane rentalsPane;
        try {
            rentalsPane = loader.load();
            if(loader.getController() instanceof ViewUserInformationController) {

                ((ViewUserInformationController)loader.getController()).setParentController(this);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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

    // Refreshes the dashboard upon date change
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

    private void changeButtonsColor(JFXButton button) {
        String inactive = " #01A2E9"; // Example hex color code
        String active = "#f77272"; // Example hex color code
    
        agentsButton.setStyle("-fx-background-color: " + inactive + ";");
        rentalsButton.setStyle("-fx-background-color: " + inactive + ";");
        transactionsButton.setStyle("-fx-background-color: " + inactive + ";");
        rentersButton.setStyle("-fx-background-color: " + inactive + ";");
        reportButton.setStyle("-fx-background-color: " + inactive + ";");

        button.setStyle("-fx-background-color: " + active + ";");
    }

    public void setProfileView() throws SQLException {

        Image image;

        image = SQLHandlerUtil.getProfile(Account.getUserID());

        if(image != null) {

            double width = image.getWidth();
            double height = image.getHeight();
            double scale = Math.min(100 / width, 100 / height);

            profileView.setImage(image);
            profileView.setFitWidth(width * scale);
            profileView.setFitHeight(height * scale);
            profileView.setPreserveRatio(true);
            profileView.setX(profileView.getX() - 5);

            double scaledWidth = width * scale;
            double scaledHeight = height * scale;
            double radius = Math.min(scaledWidth, scaledHeight) / 2;

            Circle circle = new Circle(radius);
            circle.setCenterX(scaledWidth / 2);
            circle.setLayoutX(circle.getLayoutX() - 5);
            circle.setCenterY(scaledHeight / 2);
            profileView.setClip(circle);

            Circle border = new Circle(radius);
            border.setCenterX(scaledWidth / 2);
            border.setCenterY(scaledHeight / 2);
            border.setStroke(Color.BLACK);
            border.setStrokeWidth(2);
            border.setFill(null);
            border.setLayoutX(border.getLayoutX()-3);
            border.setLayoutY(border.getLayoutY()+1);

            profilePane.getChildren().add(border);
        }
    }
}

