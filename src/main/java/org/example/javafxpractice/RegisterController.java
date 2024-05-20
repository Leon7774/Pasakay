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
    Button closeButton2;

    public void closeButtonOnActionEvent(ActionEvent event) throws IOException {
        Stage stage = (Stage) closeButton2.getScene().getWindow();
        stage.close();
        BaseUI login = new BaseUI("/fxml/login.fxml");
    }
}
