package org.example.javafxpractice.controllers;

import com.jfoenix.controls.JFXListView;
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
import org.example.javafxpractice.objects.Account;
import org.example.javafxpractice.objects.Property;
import org.example.javafxpractice.util.FXMLLoaderUtil;

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
    private Text welcomeLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
    }

    public void initializeTable() {

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        for (int i = 0; i < Account.getPropertyList().toArray().length; i++) {
            System.out.println(Account.getPropertyList().get(i).getAddress());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/propertyunit.fxml"));

                HBox hbox = loader.load();
                PropertyItemController pipController = loader.getController();
                pipController.setData(Account.getPropertyList().get(i));
                vboxContent.getChildren().add(hbox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void onTenantTabClick(ActionEvent event) throws IOException {
        dashboardContent.getChildren().clear();
        dashboardContent.getChildren().setAll(FXMLLoaderUtil.getInstance().load("/fxml/node2.fxml"));
    }

    public void onPropertiesTabClick(ActionEvent event) throws IOException {
        dashboardContent.getChildren().clear();

        // Load the new FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/propertytab.fxml"));
        BorderPane propertyTabPane = loader.load(); // Load the content first

        // Get the controller of the new FXML
        PropertyItemController propertyTabController = loader.getController(); // Retrieve the controller after loading

        // Pass the current instance of DashboardController to the new controller
        propertyTabController.setParentController(this);

        // Set the new content
        dashboardContent.getChildren().setAll(propertyTabPane);

        // Initialize the table if needed
        initializeTable();
    }

    public AnchorPane getDashboardContent() {
        return dashboardContent;
    }

}
