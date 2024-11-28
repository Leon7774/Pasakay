package org.example.javafxpractice;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.javafxpractice.objects.Property;
import org.example.javafxpractice.util.SQLHandlerUtil;
import org.example.javafxpractice.util.StageUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    public static void main(String[] args) throws SQLException {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        StageUtil login = new StageUtil("/fxml/login.fxml");
        System.out.println("$");
    }
}
