package org.example.javafxpractice.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.example.javafxpractice.objects.Property;
import org.example.javafxpractice.util.FXMLLoaderUtil;
import org.example.javafxpractice.util.StageUtil;
import java.io.IOException;

public class PropertyItemController {
    @FXML
    public Label propertyName;
    public Label propertyID;
    public Label propertyAddress;
    public Label availableUnits;
    public Label netIncome;
    public Label propertyDescription;
    public Label occupiedUnits;
    public Label monthlyPerUnit;
    public Label monthlyTax;
    public JFXButton editButton;
    public JFXButton deleteButton;
    private StageUtil stage;
    private boolean editOpen;

    public Property propertyA;

    public DashboardController dashboardController;

    public void setParentController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    public void setData(Property property) {
        propertyAddress.setText(property.getAddress());
        availableUnits.setText("Available Units: " + property.getAvailableUnits());
        netIncome.setText("Net Income: $" + String.format("%.2f", property.getIncome()));
        propertyDescription.setText(property.getDescription());
        propertyName.setText(property.getName());
        propertyID.setText("ID: "+ property.getPropertyID());
        monthlyPerUnit.setText("Monthly Rent per Unit: $" + String.format("%.2f", property.getUnitMonthly()));
        occupiedUnits.setText("Occupied Units: " + property.getOccupiedUnits());
        monthlyTax.setText("Monthly Tax: $" + String.format("%.2f", property.getTax()));

        propertyA = property;
    }

    public void onEditClick(ActionEvent event) throws IOException {
        if (!editOpen) {
            stage = new StageUtil("/fxml/editProperty.fxml");
            EditPropertyController controller = (EditPropertyController) stage.getController();
            controller.setDashboardController(dashboardController); // Pass DashboardController reference
            controller.initializeData(this);
            editOpen = true;
        }
    }

    public void setEditButton(boolean b) {
        editOpen = b;
    }

    public void onAddTenant() throws IOException {
        stage = new StageUtil("/fxml/registertenant.fxml");
        RegisterTenantController controller = (RegisterTenantController) stage.getController();
        controller.setPropertyItemController(this);
    }

}
