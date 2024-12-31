package main.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import main.util.SQLHandlerUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewInactiveCarsController implements Initializable {

    @FXML
    private ImageView closeButton;

    @FXML
    private AnchorPane dashboardContent;

    @FXML
    private Text dashboardTitle;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vboxContent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            initializeTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    void initializeTable() throws SQLException {

        vboxContent.getChildren().clear();

        // Disables horizontal scrolling
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        for(Car car : SQLHandlerUtil.getInactiveCarsList(Account.getUserID())) {

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitInactiveCars.fxml"));
                HBox hbox = loader.load();
                UnitInactiveCarController inactiveCarController = loader.getController();
                inactiveCarController.setData(car);
                inactiveCarController.setParentController(this);
                vboxContent.getChildren().add(hbox);
            }

            catch (IOException e) {

                throw new RuntimeException(e);
            }

        }
    }
}
