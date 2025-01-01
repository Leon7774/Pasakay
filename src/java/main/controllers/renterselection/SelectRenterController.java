package main.controllers.renterselection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

    private RegisterRentalController_Old parentController;


    @FXML
    void initialize() throws SQLException {
        initializeTable();
    }

    @FXML
    void onCancelClick(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
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
                vboxContent.setVgrow(hbox, javafx.scene.layout.Priority.ALWAYS);
            }

            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setData(Renter renter) {
        this.parentController.setRenter(renter);
    }

    public void setParentController(RegisterRentalController_Old parentController) {
        this.parentController = parentController;
    }

}
