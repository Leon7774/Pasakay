package org.example.javafxpractice;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    public TextField loginUsernameTextField;
    @FXML
    public Button loginButton;
    @FXML
    private Label loginLabel;
    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private Button closeButton;

    @FXML
    public void onClickLoginButton(ActionEvent event) {
        loginLabel.setText("You clicked login");
        loginLabel.setVisible(true);
        System.out.println("Yes");
    }

    public void closeButtonOnActionEvent(ActionEvent event) {
        System.out.println("Ye2s");
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void isValidLogin() {
    }
}
