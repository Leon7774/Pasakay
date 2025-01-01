package main.controllers.renterselection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.controllers.renterselection.UnitRenterController_Select;
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
                main.controllers.renterselection.UnitRenterController_Select unitRenterController_Select = loader.getController();
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

    @FXML
    void search(KeyEvent event) throws SQLException {
        String keyword = ((TextField)event.getSource()).getText().toLowerCase();
        vboxContent.getChildren().clear();

        for (Renter renter : SQLHandlerUtil.getRenters(Account.getUserID())) {
            if (keyword.startsWith("id=")) {
                try {
                    // Grabs the renter id
                    int id = Integer.parseInt(keyword.substring(3));
                    if (renter.getRenterID() == id) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitRenter_Select.fxml"));
                            // Makes a horizontal box for every agent
                            HBox hbox = loader.load();
                            // Grabs the controller of each agent hbox made -- This allows us to edit each hbox
                            UnitRenterController_Select unitRenterControllerSelect = loader.getController();
                            unitRenterControllerSelect.setData(renter);
                            // Passes the dashboard controller to each hbox, so that when a component is accessed from the hbox, the dashboard will be editable
                            vboxContent.getChildren().add(hbox);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } catch (NumberFormatException ignored) {}
            } else if (renter.getFirstName().toLowerCase().startsWith(keyword) || renter.getLastName().toLowerCase().startsWith(keyword)) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitRenter_Select.fxml"));
                    // Makes a horizontal box for every agent
                    HBox hbox = loader.load();
                    // Grabs the controller of each agent hbox made -- This allows us to edit each hbox
                    UnitRenterController_Select unitRenterControllerSelect = loader.getController();
                    unitRenterControllerSelect.setData(renter);
                    // Passes the dashboard controller to each hbox, so that when a component is accessed from the hbox, the dashboard will be editable
                    vboxContent.getChildren().add(hbox);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
