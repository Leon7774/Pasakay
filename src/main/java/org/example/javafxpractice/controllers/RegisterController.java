package org.example.javafxpractice.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.javafxpractice.util.StageUtil;
import org.example.javafxpractice.util.SQLHandlerUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

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
    public AnchorPane registerPane;

    public void closeButtonOnActionEvent(ActionEvent event) throws IOException {
        Stage stage = (Stage) closeButton2.getScene().getWindow();
        stage.close();
        StageUtil login = new StageUtil("/fxml/login.fxml");
    }

    public void registerButtonOnEvent(ActionEvent event) throws SQLException, IOException {
        passwordLabel.setVisible(false);
        passwordLabel.setText("Passwords do not match");
        System.out.println("clicked");

        try{
            if(firstNameTextField.getText().equals("") && lastNameTextField.getText().equals("") && usernameTextField.getText().equals("")) {
                passwordLabel.setText("Please fill out all the fields");
                passwordLabel.setVisible(true);
            } else if (firstNameTextField.getText().length() > 50) {
                passwordLabel.setText("Name is over limit (50)");
                passwordLabel.setVisible(true);

            } else {
                if (passwordTextField1.getText().isBlank()) {
                    passwordLabel.setText("Please enter a password");
                    passwordLabel.setVisible(true);
                }
                // Checks if passwords match, registers if true.
                if (!passwordTextField1.getText().equals(passwordTextField2.getText())) {
                    passwordLabel.setVisible(true);
                } else {
                    System.out.println("matched");
                    SQLHandlerUtil.WriteUser(autoCapitalize(firstNameTextField.getText()), autoCapitalize(lastNameTextField.getText()), usernameTextField.getText(), passwordTextField1.getText());
                    Stage stage = (Stage) registerTextField.getScene().getWindow();
                    stage.close();
                    new StageUtil("/fxml/registerconfirmed.fxml");

                    passwordLabel.setVisible(false);
                }
            }
        }catch (SQLIntegrityConstraintViolationException e){
            passwordLabel.setVisible(true);
            passwordLabel.setText("Username is already taken");
        }

    }

    @FXML
    private void returnToLoginClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();

        new StageUtil("/fxml/login.fxml");
    }

    public String autoCapitalize(String str) {
        String[] r = str.split(" ");
        String[] c = new String[r.length];
        String d = "";
        for(int i = 0; i < r.length; i++) {
            c[i] = r[i].substring(0, 1).toUpperCase() + r[i].substring(1);
        }

        for (String i : c) {
            d += i + " ";
        }

        return d;
    }
}
