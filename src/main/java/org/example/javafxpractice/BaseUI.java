package org.example.javafxpractice;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class BaseUI extends Stage{
    public Stage stage;
    double xOffset = 0;
    double yOffset = 0;

    public BaseUI(String path) throws IOException {
        stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(path));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("FreeHold");
        stage.setScene(new Scene(root));


        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        stage.show();
    }
}
