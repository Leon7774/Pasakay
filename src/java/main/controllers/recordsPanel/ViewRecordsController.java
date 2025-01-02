package main.controllers.recordsPanel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.controllers.recordsPanel.SelectAgentController;
import main.objects.Agent;
import main.objects.Car;
import main.util.StageUtil;

import java.io.IOException;
import java.sql.SQLException;

public class ViewRecordsController {

    @FXML
    private Text agentIDLabel;

    @FXML
    private Text agentLabel;

    @FXML
    private Text carIDLabel;

    @FXML
    private Text carLabel;

    @FXML
    private Text carLabel1;

    @FXML
    private ImageView closeButton;

    @FXML
    private AnchorPane dashboardContent;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vboxContent;

    private Agent activeAgent;
    private Car activeCar;

    @FXML
    void initialize() {
        this.agentIDLabel.setText("");
        this.agentLabel.setText("All Agents");
        this.carIDLabel.setText("");
        this.carLabel.setText("All Cars");
    }

    @FXML
    void chooseAgent(ActionEvent event) throws IOException {
        StageUtil chooseAgent = new StageUtil("/fxml/selectAgent.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());
        SelectAgentController controller = chooseAgent.getLoader().getController();
        controller.setParentController(this);
    }

    @FXML
    void chooseCar(ActionEvent event) throws IOException, SQLException {
        StageUtil chooseAgent = new StageUtil("/fxml/selectCar.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());
        SelectCarController controller = chooseAgent.getLoader().getController();
        controller.setParentController(this);
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

    @FXML
    void search(KeyEvent event) {

    }

    Agent getAgent() {
        return activeAgent;
    }

    void setAgent(Agent agent) {
        this.activeAgent = agent;
        this.agentIDLabel.setText(String.valueOf(agent.getAgentID()));
        this.agentLabel.setText(agent.getFirstname() + " " + agent.getLastname());
    }

    Car car() {
        return activeCar;
    }

    void setCar(Car car) {
        this.activeCar = car;
        this.carIDLabel.setText("ID: " + String.valueOf(car.getCar_id()));
        this.carLabel.setText(car.getCar_make() + " " + car.getCar_model() + " " + car.getCar_year());
    }



}
