package main.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.util.SQLHandlerUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterTenantController implements Initializable {

    public JFXButton addButton;
    public TextField registerAge;
    public TextField registerFirstName;
    public TextField registerLastName;
    public JFXButton cancelButton;
    public Label emptyWarningLabel;
    public RadioButton maleSelected;
    public RadioButton femaleSelected;
    public RadioButton singleSelected;
    public RadioButton marriedSelected;
    public RadioButton windowedSelected;

    private PropertyItemController controller;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setPropertyItemController(PropertyItemController controller) {
        this.controller = controller;
    }

    public void applyButtonClicked() throws SQLException, IOException {

        String firstname = registerFirstName.getText();
        String lastname = registerLastName.getText();
        String sex;

        if (maleSelected.isSelected()) {
            sex = "Male";
        } else {
            sex = "Female";
        }

        int age = Integer.parseInt(registerAge.getText()) ;
        String status = "";
        if (singleSelected.isSelected()) {
            status = "Single";
        } else if (marriedSelected.isSelected()) {
            status = "Married";
        } else if (windowedSelected.isSelected()) {
            status = "Widowed";
        }

        int propertyID = controller.propertyA.getPropertyID();

        SQLHandlerUtil.saveTenant(firstname, lastname, age, status, sex, propertyID);
        controller.propertyA.addTenant();
        controller.dashboardController.refreshTable();

        Stage stage = (Stage) femaleSelected.getScene().getWindow();


        stage.close();
    }

    public void onCancelClick() {
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.close();
    }


}
