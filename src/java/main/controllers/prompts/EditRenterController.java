package main.controllers.prompts;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.controllers.views.ViewRenterController;
import main.objects.Renter;
import main.util.SQLHandlerUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditRenterController {

    @FXML
    private TextField agePrompt, contactNumberPrompt, firstNamePrompt, lastNamePrompt, licenseNumberPrompt;

    @FXML
    private JFXButton cancelButton, scheduleButton;

    @FXML
    private Label emptyFieldWarning;

    @FXML
    private Text emptyWarningLabel;

    @FXML
    private JFXComboBox<String> sexPrompt;

    @FXML
    private JFXComboBox<String> statusPrompt;

    private Renter renter;
    private ViewRenterController viewRenterController;

    @FXML
    void onCancelClick(ActionEvent event) {

        ((Stage)agePrompt.getScene().getWindow()).close();
    }

    @FXML
    void onScheduleClick(ActionEvent event) throws SQLException {

        String firstName = firstNamePrompt.getText();
        String lastName = lastNamePrompt.getText();
        String status = statusPrompt.getValue();
        String sex = sexPrompt.getValue();
        String age = agePrompt.getText();
        String contactNumber = contactNumberPrompt.getText().trim();
        String licenseNumber = licenseNumberPrompt.getText().trim();

        if(firstName.isEmpty() || lastName.isEmpty() || status == null || sex == null || age.isEmpty() || contactNumber.isEmpty() || licenseNumber.isEmpty()) {
            emptyFieldWarning.setVisible(true);
            return;
        }

        SQLHandlerUtil.updateRenter(renter.getRenterID(), firstName, lastName, Integer.parseInt(age), status, sex, Integer.parseInt(contactNumber), Integer.parseInt(licenseNumber));
        viewRenterController.initializeTable();
        ((Stage)agePrompt.getScene().getWindow()).close();
    }

    public void setData(Renter renter) {

        emptyWarningLabel.setVisible(false);

        this.renter = renter;

        String [] sex = {"Male", "Female", "Others"};
        String [] status = {"Single", "Married", "Complicated"};

        sexPrompt.getItems().addAll(sex);
        statusPrompt.getItems().addAll(status);

        agePrompt.setText(String.valueOf(renter.getAge()));
        contactNumberPrompt.setText(String.valueOf(renter.getContact_number()));
        firstNamePrompt.setText(renter.getFirstName());
        lastNamePrompt.setText(renter.getLastName());
        licenseNumberPrompt.setText(String.valueOf(renter.getLicense_number()));
        sexPrompt.getSelectionModel().select(renter.getSex());
        statusPrompt.getSelectionModel().select(renter.getStatus());
    }

    public void setViewRenterController(ViewRenterController viewRenterController) {this.viewRenterController = viewRenterController;}
}
