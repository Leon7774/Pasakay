package org.example.javafxpractice.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.example.javafxpractice.objects.Tenant;

import java.io.IOException;
import java.sql.PreparedStatement;

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
