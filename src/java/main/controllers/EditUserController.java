package main.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.AccessibleAction;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Main;
import main.controllers.views.ViewUserInformationController;
import main.objects.Account;
import main.util.SQLHandlerUtil;

import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class EditUserController {

    @FXML
    private JFXButton applyButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private Text emptyWarningLabel;

    @FXML
    private TextField firstNamePrompt;

    @FXML
    private TextField lastNamePrompt;

    @FXML
    private PasswordField newPasswordPrompt;

    @FXML
    private PasswordField oldPasswordPrompt;

    @FXML
    private TextField userNamePrompt;

    private ViewUserInformationController parentController;

    @FXML
    void applyButtonClicked(ActionEvent event) throws Exception {

        String firstName = firstNamePrompt.getText().trim();
        String lastName = lastNamePrompt.getText().trim();
        String userName = userNamePrompt.getText().trim();
        String newPassword = newPasswordPrompt.getText().trim();
        String oldPassword = oldPasswordPrompt.getText().trim();

        if(firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty() || newPassword.isEmpty() || oldPassword.isEmpty()) {
            emptyWarningLabel.setVisible(true);
            return;
        }

        else if(!oldPassword.equals(SQLHandlerUtil.getPassword(Account.getUserID()))) {

            new Alert(Alert.AlertType.ERROR, "Old Password is incorrect.",  ButtonType.OK).show();
            return;
        }

        SQLHandlerUtil.updateUser(Account.getUserID(), firstName, lastName, userName, newPassword);
        ((Stage) applyButton.getScene().getWindow()).close();
        parentController.close();
        new Main().start(new Stage());
    }

    @FXML
    void onCancelClick(ActionEvent event) {

        ((Stage)cancelButton.getScene().getWindow()).close();
    }

    public void setData() {

        firstNamePrompt.setText(Account.getFirstName());
        lastNamePrompt.setText(Account.getLastName());
        userNamePrompt.setText(Account.getUserName());
    }

    public void setParentController(ViewUserInformationController parentController) {

        this.parentController = parentController;
    }
}
