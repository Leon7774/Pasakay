package org.example.javafxpractice.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.javafxpractice.Main;
import org.example.javafxpractice.objects.Account;
import org.example.javafxpractice.objects.Property;
import org.example.javafxpractice.util.FXMLLoaderUtil;
import org.example.javafxpractice.util.StageUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private JFXListView<Label> listview;
    private Stage stage;

    @FXML
    private AnchorPane dashboardContent;
    @FXML
    private VBox vboxContent;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private JFXButton addPropertyButton;
    @FXML
    private Label welcomeLabel;
    private static boolean opened = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO FIX LABEL
        setWelcomeLabel();
        initializeTable();
    }

    public void setWelcomeLabel() {
        if (!opened) {
            welcomeLabel.setText("Welcome, " + Account.getFirstName());
            opened = true;
        }
    }

    public void initializeTable() {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        for (Property property : Account.getPropertyList()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/propertyunit.fxml"));
                HBox hbox = loader.load();
                PropertyItemController pipController = loader.getController();
                pipController.setData(property);
                pipController.setParentController(this); // Set parent controller
                vboxContent.getChildren().add(hbox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void onTenantTabClick(ActionEvent event) throws IOException {
        dashboardContent.getChildren().setAll(FXMLLoaderUtil.getInstance().load("/fxml/node2.fxml"));

    }

    public void onPropertiesTabClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/propertytab.fxml"));
        BorderPane propertyTabPane = loader.load(); // Load the content first
        dashboardContent.getChildren().setAll(propertyTabPane);
        initializeTable();
    }

    public void onLogoutTabClick(ActionEvent event) {
        // Close the current stage
        Stage currentStage = (Stage) dashboardContent.getScene().getWindow();
        currentStage.close();

        // Launch a new login stage
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
                Stage loginStage = new Stage();
                loginStage.setTitle("Login");
                loginStage.setScene(new javafx.scene.Scene(loader.load()));
                loginStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    public void refreshTable() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/propertytab.fxml"));
        BorderPane propertyTabPane = loader.load(); // Load the content first
        dashboardContent.getChildren().setAll(propertyTabPane);
        initializeTable();
    }

    public VBox getVbox() {
        return vboxContent;
    }


    @FXML
    public void propertyAdd() throws IOException {
        StageUtil addProperty = new StageUtil("/fxml/addProperty.fxml");
        RegisterPropertyController controller = (RegisterPropertyController) addProperty.getController();
        controller.setDashboardController(this);
    }

    public void fastForward() {
        Account.passTime();
    }
}

