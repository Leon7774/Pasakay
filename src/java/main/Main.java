package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.util.StageUtil;

import java.sql.SQLException;

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
