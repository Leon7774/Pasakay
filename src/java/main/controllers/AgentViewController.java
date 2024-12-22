package main.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.objects.Account;
import javafx.stage.Stage;
import main.objects.Agent;
import main.objects.Car;
import main.util.FXMLLoaderUtil;
import main.util.SQLHandlerUtil;
import main.util.SceneUtil;
import main.util.StageUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AgentViewController {

    @FXML
    private JFXButton addCarButton;

    @FXML
    private Text agentAddress;

    @FXML
    private Text agentAge;

    @FXML
    private Text agentContact;

    @FXML
    private Text agentID;

    @FXML
    private AnchorPane dashboardContent;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Text agentName;

    @FXML
    private VBox vboxContent;

    @FXML
    private JFXButton backButton;

    @FXML
    private ImageView closeButton;


    private Agent activeAgent;

    public DashboardController parentController;


    @FXML
    void carAdd(ActionEvent event) throws IOException{
        StageUtil addCar = new StageUtil("/fxml/registerCar.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());
        RegisterCarController controller = addCar.getLoader().getController();
        controller.setParentController(this);
    }

    @FXML
    public void initialize() {
        System.out.println("Hello World");
        scrollPane.setStyle("-fx-background: white;");
    }

    @FXML
    void onBackClick(ActionEvent event) throws IOException {
        SceneUtil sceneUtil = new SceneUtil("/fxml/dashboard.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());
    }

    public void setAgentData(Agent agent) throws SQLException {
        this.activeAgent = agent;
        this.agentName.setText(activeAgent.getFirstname()+" "+activeAgent.getLastname());
        this.agentID.setText(String.valueOf(activeAgent.getAgentID()));
        this.agentAddress.setText(activeAgent.getAddress());
        this.agentAge.setText(String.valueOf(activeAgent.getAge()));
        this.agentContact.setText(String.valueOf(activeAgent.getContactNumber()));

        activeAgent.setCars(SQLHandlerUtil.getAgentCars(activeAgent.getAgentID()));
        initializeTable();

        System.out.println("bruh");
    }

    public void setParentController(DashboardController parentController) {
        this.parentController = parentController;
    }

    @FXML
    void dilightClose(MouseEvent event) {
        closeButton.setImage(new Image("/images/close-highlight.png"));
    }


    @FXML
    void highlightClose(MouseEvent event) {
        closeButton.setImage(new Image("/images/close.png"));
    }


    public void closeButtonOnActionEvent(MouseEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


    public void initializeTable() throws SQLException {

        vboxContent.getChildren().clear();

        // Disables horizontal scrolling
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        for (Car car : activeAgent.getCars()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/carUnit.fxml"));
                // Makes a horizontal box for every agent
                HBox hbox = loader.load();
                // Grabs the controller of each agent hbox made -- This allows us to edit each hbox
                CarUnitController carUnitController = loader.getController();
                carUnitController.setData(car);
                // Passes the dashboard controller to each hbox, so that when a component is accessed from the hbox, the dashboard will be editable
                carUnitController.setParentController(this); // Without this, any user input that happened inside the hbox would not be able to affec the dashboard
                vboxContent.getChildren().add(hbox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public Agent getActiveAgent() {
        return activeAgent;
    }

    public VBox getVboxContent() {
        return vboxContent;
    }
}
