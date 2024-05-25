package org.example.javafxpractice.controllers;

import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.javafxpractice.objects.Property;
import org.example.javafxpractice.util.FXMLLoaderUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private JFXListView<Label> listview;
    private Stage stage;
    @FXML
    private AnchorPane dashboardContent;
    @FXML
    private VBox vboxContent;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Text welcomeLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        List<Property> propertyList = propertyList();

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        for (int i = 0; i < propertyList.toArray().length; i++) {
            System.out.println(propertyList.get(i).getAddress());
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/propertyunit.fxml"));
                HBox hbox = loader.load();
                PropertyItemController pipController = loader.getController();
                pipController.setData(propertyList.get(i));
                vboxContent.getChildren().add(hbox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private List<Property> propertyList() {
        List<Property> ls = new ArrayList<>();

        Property property1 = new Property("Valencia Factory", "0485 Lim Street, Davao City", "Leon","Commercial Factory", true, 4000, 1, 40,304021);
        ls.add(property1);

        Property property2 = new Property("Dream Apartments", "482 Lim Street, Gensan City", "Pacs", "Apartment Complex", true, 4200, 2, 3,509139);
        ls.add(property2);

        Property property3 = new Property("Dream Apartments", "482 Lim Street, Gensan City", "Pacs", "Apartment Complex", true, 4200, 2, 3,509139);
        ls.add(property3);

        Property property4 = new Property("Dream Apartments", "482 Lim Street, Gensan City", "Pacs", "Apartment Complex", true, 4200, 2, 3,509139);
        ls.add(property4);

        Property property5 = new Property("Dream Apartments", "482 Lim Street, Gensan City", "Pacs", "Apartment Complex", true, 4200, 2, 3,509139);
        ls.add(property5);

        Property property6 = new Property("Dream Apartments", "482 Lim Street, Gensan City", "Pacs", "Apartment Complex", true, 4200, 2, 3,509139);
        ls.add(property6);

        return ls;
    }

    public void onTenantTabClick(ActionEvent event) throws IOException {
        dashboardContent.getChildren().clear();
        dashboardContent.getChildren().setAll(FXMLLoaderUtil.getInstance().load("/fxml/node2.fxml"));

    }
}
