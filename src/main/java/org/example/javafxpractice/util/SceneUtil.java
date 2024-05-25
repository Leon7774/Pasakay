package org.example.javafxpractice.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneUtil {
    private Stage stage;
    private double xOffset = 0;
    private double yOffset = 0;

    public SceneUtil(String path, Stage existingStage) throws IOException {
        this.stage = existingStage;
        Parent root = FXMLLoader.load(getClass().getResource(path));
        stage.setResizable(false);
        stage.setTitle("FreeHold");
        Scene scene = new Scene(root);
        stage.setScene(scene);

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

    public void setScene(String path) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        stage.getScene().setRoot(root);
    }

    public void showModal(String path) throws IOException {
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initOwner(stage);
        Parent root = FXMLLoader.load(getClass().getResource(path));
        Scene scene = new Scene(root);
        modalStage.setScene(scene);
        modalStage.show();
    }
}
