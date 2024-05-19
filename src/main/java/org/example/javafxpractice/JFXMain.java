package org.example.javafxpractice;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class JFXMain extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();

        Scene scene = new Scene(root);
        Stage stage1 = new Stage();

        Image icon = new Image("icon.png");

        stage1.getIcons().add(icon);
        stage1.setScene(scene);
        stage1.setMaxHeight(500);
        stage1.show();



    }
}
