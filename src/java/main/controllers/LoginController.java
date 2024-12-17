package main.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import main.objects.Account;
import main.util.SQLHandlerUtil;
import main.util.StageUtil;
import main.util.DatabaseConnection;
import main.util.SceneUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private ImageView closeButton;

    @FXML
    private JFXButton loginButton;

    @FXML
    private Label loginLabel;

    @FXML
    private PasswordField loginPasswordField;

    @FXML
    private TextField loginUsernameTextField;

    @FXML
    private JFXButton registerButton;


    public void onClickLoginButton(ActionEvent event) {
        if (loginUsernameTextField.getText().isBlank()) {
            loginLabel.setText("Username is empty");
            loginLabel.setVisible(true);
        } else if (loginPasswordField.getText().isBlank()) {
            loginLabel.setText("Password is empty");
            loginLabel.setVisible(true);
        } else {
            isValidLogin();
            System.out.println("Yes");
        }
    }

    public void closeButtonOnActionEvent(ActionEvent event) {
        System.out.println("Ye2s");
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Attach an event handler for the Enter key on the password field
        loginPasswordField.setOnAction(event -> onClickLoginButton(event));
        loginUsernameTextField.setOnAction(event -> onClickLoginButton(event));

        // Attach event handler for the close button
        closeButton.setOnMouseClicked(event -> {
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        });
    }

    @FXML
    void dilightClose(MouseEvent event) {
        closeButton.setImage(new Image("/images/close-highlight.png"));
    }


    @FXML
    void highlightClose(MouseEvent event) {
        closeButton.setImage(new Image("/images/close.png"));
    }



    // Login Checker
    public void isValidLogin() {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection1 = dbConnection.getConnection();

        String verifyLogin = "SELECT count(1) FROM user WHERE userName = '" + loginUsernameTextField.getText() + "'AND password ='" + loginPasswordField.getText() + "'";

        try {
            Statement statement = connection1.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    //Sets active account based on found account
                    SQLHandlerUtil.findUser(loginUsernameTextField.getText());
                    Account.printActiveAccount();
                    SQLHandlerUtil.loadCarType();
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.close();
                    StageUtil ui = new StageUtil("/fxml/dashboard.fxml");
                    loginLabel.setText("Login Sucessful");
                    loginLabel.setVisible(true);
                }else{
                    loginLabel.setText("No matches for that user/password combination was found.");
                    loginLabel.setVisible(true);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void registerClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) registerButton.getScene().getWindow();
        stage.close();
        System.out.println("register clicked");

        SceneUtil baseUI = new SceneUtil("/fxml/register.fxml", stage);

        // Optionally, if you need to change the scene later:
        // baseUI.setScene("/fxml/otherScene.fxml");
    }


}
