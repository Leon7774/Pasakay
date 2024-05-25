package org.example.javafxpractice.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class FXMLLoaderUtil {

    private static FXMLLoaderUtil instance;

    private FXMLLoaderUtil() {

    }

    public static synchronized FXMLLoaderUtil getInstance() {
        if (instance == null) {
            instance = new FXMLLoaderUtil();
        }
        return instance;
    }

    public Parent load(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        return loader.load();
    }
}
