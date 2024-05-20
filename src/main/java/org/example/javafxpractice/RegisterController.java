package org.example.javafxpractice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;

public class RegisterController {

    @FXML
    public TextField firstNameTextField;
    public TextField lastNameTextField;
    public TextField usernameTextField;
    public Button registerTextField;
    public PasswordField passwordTextField1;
    public PasswordField passwordTextField2;
    public Label registerLabel;
    public Button closeButton2;
    public Label passwordLabel;

    public void closeButtonOnActionEvent(ActionEvent event) throws IOException {
        Stage stage = (Stage) closeButton2.getScene().getWindow();
        stage.close();
        BaseUI login = new BaseUI("/fxml/login.fxml");
    }

    public void registerButtonOnEvent(ActionEvent event) {
        passwordLabel.setVisible(false);
        passwordLabel.setText("Passwords do not match");
        System.out.println("clicked");
        if(!passwordTextField1.getText().isBlank()){
            if (!passwordTextField1.getText().equals(passwordTextField2.getText())) {
                passwordLabel.setVisible(true);
            } else {
                System.out.println("matched");
                passwordLabel.setVisible(false);
            }
        }else{
            passwordLabel.setText("Please enter a password");
            passwordLabel.setVisible(true);
        }

    }

    public void createAccount() {

    }
}
