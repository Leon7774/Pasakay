package main.controllers.recordsPanel;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.controllers.RegisterCarController;
import main.controllers.recordsPanel.SelectAgentController;
import main.controllers.units.UnitRentalController;
import main.controllers.views.ViewTransactionController;
import main.objects.*;
import main.util.SQLHandlerUtil;
import main.util.StageUtil;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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

    @FXML
    private JFXButton chooseCarButton;

    @FXML
    private JFXButton clearAgentButton;

    @FXML
    private JFXButton clearCarButton;

    @FXML
    private Text recordCount;

    @FXML
    private JFXToggleButton recordToggle;

    @FXML
    private DatePicker dateFromButton;

    @FXML
    private DatePicker dateToButton;


    private Agent activeAgent;
    private Car activeCar;
    private boolean toggled = false;
    private LocalDate fromDate = LocalDate.MIN;
    private LocalDate toDate = LocalDate.MAX;

    @FXML
    void initialize() throws SQLException {
        this.agentIDLabel.setText("");
        this.agentLabel.setText("All Agents");
        this.carIDLabel.setText("");
        this.carLabel.setText("All Cars");
        scrollPane.setStyle("-fx-background: white;");
        initializeTable();
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


    @FXML
    void toPicked(ActionEvent event) throws SQLException {
        this.toDate = dateToButton.getValue();
        initializeTable();
    }

    @FXML
    void fromPicked(ActionEvent event) throws SQLException {
        this.fromDate = dateFromButton.getValue();
        initializeTable();
    }

    public void closeButtonOnActionEvent(MouseEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


    @FXML
    void toggleClick(ActionEvent event) throws SQLException {
        toggled = !toggled;
        initializeTable();
    }

    Agent getAgent() {
        return activeAgent;
    }

    void setAgent(Agent agent) {
        this.activeAgent = agent;
        this.agentIDLabel.setText("ID: " + String.valueOf(agent.getAgentID()));
        this.agentLabel.setText(agent.getFirstname() + " " + agent.getLastname());
        clearAgentButton.setDisable(false);
        chooseCarButton.setDisable(false);
    }

    @FXML
    void clearAgent(ActionEvent event) throws SQLException {
        this.activeAgent = null;
        this.agentIDLabel.setText("");
        this.agentLabel.setText("All Agents");
        clearAgentButton.setDisable(true);
        chooseCarButton.setDisable(true);
        initializeTable();
    }

    @FXML
    void clearCar(ActionEvent event) throws SQLException {
        this.activeCar = null;
        this.carIDLabel.setText("");
        this.carLabel.setText("All Cars");
        clearCarButton.setDisable(true);
        initializeTable();
    }

    Car car() {
        return activeCar;
    }

    void setCar(Car car) {
        this.activeCar = car;
        this.carIDLabel.setText("ID: " + String.valueOf(car.getCar_id()));
        this.carLabel.setText(car.getCar_make() + " " + car.getCar_model() + " " + car.getCar_year());
        clearCarButton.setDisable(false);
    }

    public void initializeTable() throws SQLException {
        if(!toggled) {
            vboxContent.getChildren().clear();

            List<Rental> rentals = Account.getRentalsList();

            if (getAgent() != null) {
                rentals = rentals.stream()
                    .filter(r -> r.getAgentId() == getAgent().getAgentID())
                    .collect(Collectors.toList());
            }

            if (car() != null) {
                rentals = rentals.stream()
                    .filter(r -> r.getCarId() == car().getCar_id())
                    .collect(Collectors.toList());
            }

            for(Rental rental : rentals) {
                if(rental.getRentStart().isAfter(fromDate) && rental.getRentEnd().isBefore(toDate)) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitRental.fxml"));
                        HBox hbox = loader.load();
                        UnitRentalController unitRentalController = loader.getController();
                        Car car = SQLHandlerUtil.getOneCar(rental.getCarId());
                        unitRentalController.setData(car, rental);

                        vboxContent.getChildren().add(hbox);

                    }

                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }



            }

            recordCount.setText(String.valueOf(rentals.size()) + " records found");
        }else{
            vboxContent.getChildren().clear();
            int counter = 0;


            List<RentalTransaction> transactions = SQLHandlerUtil.getRentalTransactions(Account.getUserID());

            for(RentalTransaction transaction : transactions) {
                if(Account.getRental(transaction.getRentalID()).getRentStart().isAfter(fromDate) && Account.getRental(transaction.getRentalID()).getRentEnd().isBefore(toDate)) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitTransaction.fxml"));
                        HBox hbox = loader.load();
                        ViewTransactionController transactionController = loader.getController();
                        transactionController.setData(transaction.getTransactionID(), transaction.getRentalID(), transaction.getDate(), transaction.getAmount(), transaction.getTransactionName());

                        if(getAgent() == null) {
                            counter++;
                            vboxContent.getChildren().add(hbox);
                        }else if(transaction.getAgentID() == getAgent().getAgentID() && activeCar == null) {
                            counter++;
                            vboxContent.getChildren().add(hbox);
                        } else if (transaction.getAgentID() == getAgent().getAgentID() && transaction.getCarID() == activeCar.getCar_id()) {
                            counter++;
                            vboxContent.getChildren().add(hbox);
                        }


                    }
                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }

            recordCount.setText(String.valueOf(counter) + " records found");
        }

    }


    @FXML
    void search(KeyEvent event) throws SQLException {
        if (toggled) {
            String keyword = ((TextField) event.getSource()).getText().toLowerCase();
            vboxContent.getChildren().clear();

            for (RentalTransaction transaction : SQLHandlerUtil.getRentalTransactions(Account.getUserID())) {
                if (Account.getRental(transaction.getRentalID()).getRentStart().isAfter(fromDate) && Account.getRental(transaction.getRentalID()).getRentEnd().isBefore(toDate)) {
                    if (keyword.startsWith("name=")) {

                        String name = keyword.substring("name=".length());
                        if (transaction.getTransactionName().toLowerCase().equals(name)) {

                            try {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitTransaction.fxml"));
                                HBox hbox = loader.load();
                                ViewTransactionController transactionController = loader.getController();
                                transactionController.setData(transaction.getTransactionID(), transaction.getRentalID(), transaction.getDate(), transaction.getAmount(), transaction.getTransactionName());
                                vboxContent.getChildren().add(hbox);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } else if (keyword.startsWith("date=")) {

                        String date = keyword.substring("date=".length());
                        if (transaction.getDate().toLowerCase().equals(date)) {

                            try {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitTransaction.fxml"));
                                HBox hbox = loader.load();
                                ViewTransactionController transactionController = loader.getController();
                                transactionController.setData(transaction.getTransactionID(), transaction.getRentalID(), transaction.getDate(), transaction.getAmount(), transaction.getTransactionName());
                                vboxContent.getChildren().add(hbox);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } else if (keyword.startsWith("amount=")) {

                        double amount = Double.parseDouble(keyword.substring("amount=".length()));
                        if (transaction.getAmount() == amount) {

                            try {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitTransaction.fxml"));
                                HBox hbox = loader.load();
                                ViewTransactionController transactionController = loader.getController();
                                transactionController.setData(transaction.getTransactionID(), transaction.getRentalID(), transaction.getDate(), transaction.getAmount(), transaction.getTransactionName());
                                vboxContent.getChildren().add(hbox);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } else if (keyword.startsWith("rental_id=")) {

                        int rentalID = Integer.parseInt(keyword.substring("rental_id=".length()));
                        if (transaction.getRentalID() == rentalID) {

                            try {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitTransaction.fxml"));
                                HBox hbox = loader.load();
                                ViewTransactionController transactionController = loader.getController();
                                transactionController.setData(transaction.getTransactionID(), transaction.getRentalID(), transaction.getDate(), transaction.getAmount(), transaction.getTransactionName());
                                vboxContent.getChildren().add(hbox);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } else if (keyword.startsWith("car_id=")) {

                        int carID = Integer.parseInt(keyword.substring("car_id=".length()));
                        Rental rental = SQLHandlerUtil.getOneRental(transaction.getRentalID());
                        if (rental.getCarId() == carID) {

                            try {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitTransaction.fxml"));
                                HBox hbox = loader.load();
                                ViewTransactionController transactionController = loader.getController();
                                transactionController.setData(transaction.getTransactionID(), transaction.getRentalID(), transaction.getDate(), transaction.getAmount(), transaction.getTransactionName());
                                vboxContent.getChildren().add(hbox);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } else if (keyword.startsWith("agent_id=")) {

                        int agentID = Integer.parseInt(keyword.substring("agent_id=".length()));
                        Rental rental = SQLHandlerUtil.getOneRental(transaction.getRentalID());
                        if (rental.getAgentId() == agentID) {

                            try {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitTransaction.fxml"));
                                HBox hbox = loader.load();
                                ViewTransactionController transactionController = loader.getController();
                                transactionController.setData(transaction.getTransactionID(), transaction.getRentalID(), transaction.getDate(), transaction.getAmount(), transaction.getTransactionName());
                                vboxContent.getChildren().add(hbox);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } else if (keyword.startsWith("id=")) {
                        int transactionID = Integer.parseInt(keyword.substring("id=".length()));
                        if (transaction.getTransactionID() == transactionID) {
                            try {

                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitTransaction.fxml"));
                                HBox hbox = loader.load();
                                ViewTransactionController transactionController = loader.getController();
                                transactionController.setData(transaction.getTransactionID(), transaction.getRentalID(), transaction.getDate(), transaction.getAmount(), transaction.getTransactionName());
                                vboxContent.getChildren().add(hbox);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } else if (String.valueOf(transaction.getTransactionID()).startsWith(keyword)) {

                        try {

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitTransaction.fxml"));
                            HBox hbox = loader.load();
                            ViewTransactionController transactionController = loader.getController();
                            transactionController.setData(transaction.getTransactionID(), transaction.getRentalID(), transaction.getDate(), transaction.getAmount(), transaction.getTransactionName());
                            vboxContent.getChildren().add(hbox);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        } else {
            String keyword = ((TextField) event.getSource()).getText().toLowerCase();
            vboxContent.getChildren().clear();

            for (Rental rental : Account.getRentalsList()) {
                if (rental.getRentStart().isAfter(fromDate) && rental.getRentEnd().isBefore(toDate)) {
                    if (keyword.startsWith("id=")) {
                        try {
                            // Grabs the renter id
                            int id = Integer.parseInt(keyword.substring(3));
                            if (rental.getId() == id) {
                                try {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitRental.fxml"));
                                    // Makes a horizontal box for every agent
                                    HBox hbox = loader.load();
                                    UnitRentalController unitRentalController = loader.getController();
                                    Car car = SQLHandlerUtil.getOneCar(rental.getCarId());
                                    unitRentalController.setData(car, rental);
                                    // Passes the dashboard controller to each hbox, so that when a component is accessed from the hbox, the dashboard will be editable
                                    vboxContent.getChildren().add(hbox);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        } catch (NumberFormatException ignored) {
                        }
                    } else if (keyword.startsWith("agent_id=")) {
                        try {
                            // Grabs the agent id
                            int agentId = Integer.parseInt(keyword.substring(9));
                            if (rental.getAgentId() == agentId) {
                                try {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitRental.fxml"));
                                    // Makes a horizontal box for every agent
                                    HBox hbox = loader.load();
                                    // Grabs the controller of each agent hbox made -- This allows us to edit each hbox
                                    UnitRentalController unitRentalController = loader.getController();
                                    Car car = SQLHandlerUtil.getOneCar(rental.getCarId());
                                    unitRentalController.setData(car, rental);
                                    // Passes the dashboard controller to each hbox, so that when a component is accessed from the hbox, the dashboard will be editable
                                    vboxContent.getChildren().add(hbox);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        } catch (NumberFormatException ignored) {
                        }
                    } else if (keyword.startsWith("renter_id=")) {
                        try {
                            // Grabs the renter id
                            int renterId = Integer.parseInt(keyword.substring(10));
                            if (rental.getRenterID() == renterId) {
                                try {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitRental.fxml"));
                                    // Makes a horizontal box for every agent
                                    HBox hbox = loader.load();
                                    // Grabs the controller of each agent hbox made -- This allows us to edit each hbox
                                    UnitRentalController unitRentalController = loader.getController();
                                    Car car = SQLHandlerUtil.getOneCar(rental.getCarId());
                                    unitRentalController.setData(car, rental);
                                    // Passes the dashboard controller to each hbox, so that when a component is accessed from the hbox, the dashboard will be editable
                                    vboxContent.getChildren().add(hbox);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        } catch (NumberFormatException ignored) {
                        }
                    } else if (SQLHandlerUtil.getOneCar(rental.getCarId()).getCar_make().toLowerCase().startsWith(keyword) || SQLHandlerUtil.getOneCar(rental.getCarId()).getCar_model().toLowerCase().startsWith(keyword)) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitRental.fxml"));
                            // Makes a horizontal box for every agent
                            HBox hbox = loader.load();
                            // Grabs the controller of each agent hbox made -- This allows us to edit each hbox
                            UnitRentalController unitRentalController = loader.getController();
                            Car car = SQLHandlerUtil.getOneCar(rental.getCarId());
                            unitRentalController.setData(car, rental);
                            // Passes the dashboard controller to each hbox, so that when a component is accessed from the hbox, the dashboard will be editable
                            vboxContent.getChildren().add(hbox);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }
}
