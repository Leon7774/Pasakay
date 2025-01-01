package main.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class StageUtil extends Stage{
    private Stage stage;
    double xOffset = 0;
    double yOffset = 0;
    FXMLLoader loader;

    public StageUtil(String path) throws IOException {
        stage = new Stage();
        loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("FreeHold");
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("/images/icon.png"));


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

    public StageUtil(String path, Stage owner) throws IOException {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(owner);
        loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("FreeHold");
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("/images/icon.png"));


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

    public StageUtil(String path, Stage owner, int num) throws IOException {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(owner);
        loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("FreeHold");
        stage.setScene(new Scene(root));
        stage.getIcons().add(new Image("/images/icon.png"));


        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

        stage.showAndWait();
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public Object getController() {
        return loader.getController(); // Returns the controller instance
    }

    public void closeStage() {
        stage.close();
    }


}
