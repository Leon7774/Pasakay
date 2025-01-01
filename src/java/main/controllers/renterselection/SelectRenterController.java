package main.controllers.renterselection;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.objects.Account;
import main.objects.Renter;
import main.util.SQLHandlerUtil;

import java.io.IOException;
import java.sql.SQLException;

public class SelectRenterController {

    @FXML
    private ImageView searchButton;

    @FXML
    private TextField searchTab;

    @FXML
    private VBox vboxContent;

    @FXML
    void initialize() throws SQLException {
        initializeTable();
    }

    @FXML
    void searchClicked(MouseEvent event) {

    }

    void initializeTable() throws SQLException {

        vboxContent.getChildren().clear();

        for(Renter renter : SQLHandlerUtil.getRenters(Account.getUserID())) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitRenter_Select.fxml"));
                HBox hbox = loader.load();
                UnitRenterController_Select unitRenterController_Select = loader.getController();
                unitRenterController_Select.setData(renter);
                unitRenterController_Select.setParentController(this);
                vboxContent.getChildren().add(hbox);
            }

            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



}
