package main.controllers.recordsPanel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.controllers.units.UnitCarController;
import main.controllers.views.ViewRentalsController;
import main.objects.Account;
import main.objects.Car;

import java.io.IOException;
import java.sql.SQLException;

public class SelectCarController {

    @FXML
    private ImageView searchButton;

    @FXML
    private TextField searchTab;

    @FXML
    private VBox vboxContent;

    private ViewRecordsController parentController;

    @FXML
    void initialize() throws SQLException {

    }

    @FXML
    void onCancelClick(ActionEvent event) {
        ((Stage)searchButton.getScene().getWindow()).close();
    }

    @FXML
    void search(KeyEvent event) {

    }

    @FXML
    void searchClicked(MouseEvent event) {

    }

    void setCar(Car car) throws SQLException {
        parentController.setCar(car);
        parentController.initializeTable();
    }


    public void initializeTable() throws SQLException {

        vboxContent.getChildren().clear();

        if (parentController.getAgent() == null) {
            for (Car car : Account.getAllCars()) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitCar-Small.fxml"));
                    // Makes a horizontal box for every agent
                    HBox hbox = loader.load();
                    // Grabs the controller of each agent hbox made -- This allows us to edit each hbox
                    UnitCarController_Small unitCarController = loader.getController();
                    unitCarController.setData(car);
                    // Passes the dashboard controller to each hbox, so that when a component is accessed from the hbox, the dashboard will be editable
                    unitCarController.setParentController(this); // Without this, any user input that happened inside the hbox would not be able to affec the dashboard
                    vboxContent.getChildren().add(hbox);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }else{
            for (Car car : parentController.getAgent().getCars()) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitCar-Small.fxml"));
                    // Makes a horizontal box for every agent
                    HBox hbox = loader.load();
                    // Grabs the controller of each agent hbox made -- This allows us to edit each hbox
                    UnitCarController_Small unitCarController = loader.getController();
                    unitCarController.setData(car);
                    // Passes the dashboard controller to each hbox, so that when a component is accessed from the hbox, the dashboard will be editable
                    unitCarController.setParentController(this); // Without this, any user input that happened inside the hbox would not be able to affec the dashboard
                    vboxContent.getChildren().add(hbox);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }


    void setParentController(ViewRecordsController viewRentalsController) throws SQLException {
        this.parentController = viewRentalsController;
        initializeTable();
    }

}
