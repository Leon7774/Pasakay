package main.controllers.views;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.controllers.EditUserController;
import main.objects.Account;
import main.util.SQLHandlerUtil;
import main.util.StageUtil;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewUserInformationController implements Initializable {

    @FXML private ImageView closeButton;
    @FXML private AnchorPane dashboardContent, imagePane;
    @FXML private Text dashboardTitle;
    @FXML private JFXButton editButton;
    @FXML private Label firstNameLabel, lastNameLabel, passwordLabel, userIDLabel, userNameLabel;
    @FXML private ImageView profileView, addIcon;

    private DashboardMain parentController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        addIcon.setVisible(false);

        setData();
        imagePane.setOnMouseEntered(event -> {

            addIcon.setVisible(true);
        });

        imagePane.setOnMouseExited(event -> {

            addIcon.setVisible(false);
        });
    }

    @FXML
    void closeButtonOnActionEvent(MouseEvent event) {
        close();
    }

    @FXML
    void dilightClose(MouseEvent event) {
        closeButton.setImage(new Image("/images/close-highlight.png"));
    }

    @FXML
    void highlightClose(MouseEvent event) {
        closeButton.setImage(new Image("/images/close.png"));
    }

    @FXML
    void onEditClick(ActionEvent event) throws IOException {

        StageUtil newStage = new StageUtil("/fxml/editUser.fxml");
        EditUserController editUserController = (EditUserController) newStage.getController();
        editUserController.setData();
        editUserController.setParentController(this);
    }

    @FXML
    void onProfileClick(MouseEvent event) throws SQLException, FileNotFoundException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an Image File");

        FileChooser.ExtensionFilter imageFilter =
                new FileChooser.ExtensionFilter("Image Files (*.jpg, *.png)", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);

        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if(selectedFile != null) {

            SQLHandlerUtil.addProfile(selectedFile, Account.getUserID());
            setData();
            parentController.setProfileView();
        }

        else {

            new Alert(Alert.AlertType.ERROR, "No image selected.",  ButtonType.OK).show();
        }
    }

    public void setData() {

        Image image;

        userIDLabel.setText("User ID: " + Account.getUserID());
        userNameLabel.setText("UserName: " + Account.getUserName());
        firstNameLabel.setText("First Name: " + Account.getFirstName());
        lastNameLabel.setText("Last Name: " + Account.getLastName());
        try {
            passwordLabel.setText("Password: " + SQLHandlerUtil.getPassword(Account.getUserID()).replaceAll(".","*"));

            image = SQLHandlerUtil.getProfile(Account.getUserID());

            if(image == null) {

                image = new Image("/images/user.png");
            }

            double width = image.getWidth();
            double height = image.getHeight();
            double scale = Math.min(200 / width, 200 / height);

            profileView.setImage(image);
            profileView.setFitWidth(width * scale);
            profileView.setFitHeight(height * scale);
            profileView.setPreserveRatio(true);

            double scaledWidth = width * scale;
            double scaledHeight = height * scale;
            double radius = Math.min(scaledWidth, scaledHeight) / 2;

            Circle circle = new Circle(radius);
            circle.setCenterX(scaledWidth / 2);
            circle.setCenterY(scaledHeight / 2);
            profileView.setClip(circle);

            Circle border = new Circle(radius);
            border.setCenterX(scaledWidth / 2);
            border.setCenterY(scaledHeight / 2);
            border.setStroke(Color.BLACK);
            border.setStrokeWidth(2);
            border.setFill(null);

            imagePane.getChildren().add(border);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setParentController(DashboardMain parentController) {

        this.parentController = parentController;
    }

    public void close() {

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
