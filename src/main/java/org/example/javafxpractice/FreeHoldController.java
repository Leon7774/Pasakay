package org.example.javafxpractice;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class FreeHoldController {

    @FXML
    private ListView<String> propertyList;

    @FXML
    private TextField newPropertyName;

    @FXML
    private void handleAddProperty() {
        String newProperty = newPropertyName.getText();
        if (!newProperty.isEmpty()) {
            propertyList.getItems().add(newProperty);
            newPropertyName.clear();
        }
    }
}
