package org.example.javafxpractice;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private JFXListView<Label> listview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }
}
