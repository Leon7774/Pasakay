package org.example.javafxpractice.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.javafxpractice.objects.Property;

public class PropertyItemController {
    @FXML
    public Label propertyName;
    public Label propertyID;
    public Label propertyAddress;
    public Label availableUnits;
    public Label netIncome;
    public Label propertyDescription;
    public JFXButton editButton;
    public JFXButton deleteButton;
    public JFXButton viewUnitsButton;

    public void setData(Property property) {
        propertyAddress.setText(property.getAddress());
        availableUnits.setText("Available Units: " + property.getAvailableUnits());
        netIncome.setText("Net Income: " + property.getIncome());
        propertyDescription.setText(property.getDescription());
        propertyName.setText(property.getName());
        propertyID.setText(String.valueOf(property.getPropertyID()));
    }
}
