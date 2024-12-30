package main;

import javafx.application.Application;
import javafx.scene.image.Image;
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
        stage.getIcons().add(new Image("/images/icon.png"));
        System.out.println("$");
    }
}
