package main.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.controllers.views.ViewAgentController;
import main.objects.Account;
import main.util.SQLHandlerUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterAgentController implements Initializable {

    @FXML
    private TextField agentAddressPrompt;

    @FXML
    private DatePicker birthdatePrompt;

    @FXML
    private TextField agentContactPrompt;

    @FXML
    private TextField agentFirstNamePrompt;

    @FXML
    private TextField agentLastNamePrompt;

    @FXML
    private JFXButton applyButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private Text emptyWarningLabel;
    public ViewAgentController registerController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (registerController == null) {
            System.out.println("It is indeed null retard");
        }
    }

    public void setDashboardController(ViewAgentController controller) {
        this.registerController = controller;
    }


    public void applyButtonClicked(ActionEvent event) throws SQLException {
        if (
                agentFirstNamePrompt.getText().isBlank() ||
                        agentLastNamePrompt.getText().isBlank() ||
                        agentAddressPrompt.getText().isBlank() ||
                        birthdatePrompt.getValue() == null ||
                        agentContactPrompt.getText().isBlank()
        ) {
            emptyWarningLabel.setVisible(true);
        } else {
            Account.getAgentList().add(
                    // SQLHandlerUtil.addAgent injects a new agent to the database, and it also returns the created agent to the source code,
                    // - which is added to the agent list of the user
                    SQLHandlerUtil.addAgent(agentFirstNamePrompt.getText(),
                            agentLastNamePrompt.getText(),
                            birthdatePrompt.getValue(),
                            agentAddressPrompt.getText(),
                            Integer.parseInt(agentContactPrompt.getText().replace(" ", "")))
            );

            //TODO make account successfully handled
            Stage stage = (Stage) emptyWarningLabel.getScene().getWindow();
            stage.close();

            SQLHandlerUtil.findUser(Account.getUserName());

            registerController.getVbox().getChildren().clear();
            registerController.initializeTable();
        }
    }

    public void onCancelClick(ActionEvent event) {
        Stage stage = (Stage) agentFirstNamePrompt.getScene().getWindow();
        stage.close();
    }


}
