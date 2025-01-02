package main.controllers.views;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
import main.controllers.prompts.SelectTransactionDatesController;
import main.objects.Account;
import main.objects.Rental;
import main.objects.RentalTransaction;
import main.util.SQLHandlerUtil;
import main.util.StageUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class ViewTransactionsController implements Initializable {

    @FXML
    private ImageView closeButton;

    @FXML
    private AnchorPane dashboardContent;

    @FXML
    private Text dashboardTitle;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private JFXButton setDatesButton, clearDateButton;

    @FXML
    private Text dateRange;

    @FXML
    private VBox vboxContent;

    private LocalDate [] dates = new LocalDate[2];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clearDateButton.setDisable(true);

        try {
            initializeTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void chooseDates(ActionEvent event) throws IOException {

        StageUtil pickDates = new StageUtil("/fxml/selectTransactionDates.fxml", ((Stage)setDatesButton.getScene().getWindow()));
        SelectTransactionDatesController controller = (SelectTransactionDatesController) pickDates.getController();
        controller.setParentController(this);
    }

    @FXML
    void clearDates(ActionEvent event) throws SQLException {

        dates = new LocalDate[2];
        dateRange.setText("Select Date Range");
        clearDateButton.setDisable(true);
        initializeTable();
    }

    @FXML
    void closeButtonOnActionEvent(MouseEvent event) {

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void dilightClose(MouseEvent event) {closeButton.setImage(new Image("/images/close-highlight.png"));}

    @FXML
    void highlightClose(MouseEvent event) {closeButton.setImage(new Image("/images/close.png"));}

    public void initializeTable() throws SQLException {

        vboxContent.getChildren().clear();
        vboxContent.setAlignment(Pos.TOP_CENTER);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            for (RentalTransaction transaction : SQLHandlerUtil.getRentalTransactions(Account.getUserID())) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitTransaction.fxml"));
                HBox hbox = loader.load();
                ViewTransactionController transactionController = loader.getController();
                transactionController.setData(transaction.getTransactionID(), transaction.getRentalID(), transaction.getDate(), transaction.getAmount(), transaction.getTransactionName());

                if(dates[0] != null) {

                    LocalDate transactionDate = LocalDate.parse(transaction.getDate(), formatter);

                    if(transactionDate.isAfter(dates[0]) && transactionDate.isBefore(dates[1])) {

                        vboxContent.getChildren().add(hbox);
                    }
                }

                else if(dates[0] == null) {

                    vboxContent.getChildren().add(hbox);
                }
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void search(KeyEvent event) throws SQLException, IOException {

        String keyword = ((TextField)event.getSource()).getText().toLowerCase();
        vboxContent.getChildren().clear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (RentalTransaction transaction : SQLHandlerUtil.getRentalTransactions(Account.getUserID())) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitTransaction.fxml"));
            HBox hbox = loader.load();
            ViewTransactionController transactionController = loader.getController();
            transactionController.setData(transaction.getTransactionID(), transaction.getRentalID(), transaction.getDate(), transaction.getAmount(), transaction.getTransactionName());

            if(dates[0] != null) {

                LocalDate transactionDate = LocalDate.parse(transaction.getDate(), formatter);

                if(transactionDate.isAfter(dates[0]) && transactionDate.isBefore(dates[1])) {

                    if (keyword.startsWith("name=")) {

                        String name = keyword.substring("name=".length());
                        if(transaction.getTransactionName().toLowerCase().equals(name)) {

                            vboxContent.getChildren().add(hbox);
                        }
                    }

                    else if (keyword.startsWith("amount=")) {

                        double amount = Double.parseDouble(keyword.substring("amount=".length()));
                        if(transaction.getAmount() == amount) {

                            vboxContent.getChildren().add(hbox);
                        }
                    }

                    else if (keyword.startsWith("rental_id=")) {

                        int rentalID = Integer.parseInt(keyword.substring("rental_id=".length()));
                        if(transaction.getRentalID() == rentalID) {

                            vboxContent.getChildren().add(hbox);
                        }
                    }

                    else if (keyword.startsWith("car_id=")) {

                        int carID = Integer.parseInt(keyword.substring("car_id=".length()));
                        Rental rental = SQLHandlerUtil.getOneRental(transaction.getRentalID());
                        if(rental.getCarId() == carID) {

                            vboxContent.getChildren().add(hbox);
                        }
                    }

                    else if (keyword.startsWith("agent_id=")) {

                        int agentID = Integer.parseInt(keyword.substring("agent_id=".length()));
                        Rental rental = SQLHandlerUtil.getOneRental(transaction.getRentalID());
                        if(rental.getAgentId() == agentID) {

                            vboxContent.getChildren().add(hbox);
                        }
                    }

                    else if (keyword.startsWith("id=")) {
                        int transactionID = Integer.parseInt(keyword.substring("id=".length()));
                        if(transaction.getTransactionID() == transactionID) {

                            vboxContent.getChildren().add(hbox);
                        }
                    }

                    else if(String.valueOf(transaction.getTransactionID()).startsWith(keyword)) {

                        vboxContent.getChildren().add(hbox);
                    }
                }
            }

            else if(dates[0] == null) {

                if (keyword.startsWith("name=")) {

                    String name = keyword.substring("name=".length());
                    if(transaction.getTransactionName().toLowerCase().equals(name)) {

                        vboxContent.getChildren().add(hbox);
                    }
                }

                else if (keyword.startsWith("date=")) {

                    String date = keyword.substring("date=".length());
                    if(transaction.getDate().toLowerCase().equals(date)) {

                        vboxContent.getChildren().add(hbox);
                    }
                }

                else if (keyword.startsWith("amount=")) {

                    double amount = Double.parseDouble(keyword.substring("amount=".length()));
                    if(transaction.getAmount() == amount) {

                        vboxContent.getChildren().add(hbox);
                    }
                }

                else if (keyword.startsWith("rental_id=")) {

                    int rentalID = Integer.parseInt(keyword.substring("rental_id=".length()));
                    if(transaction.getRentalID() == rentalID) {

                        vboxContent.getChildren().add(hbox);
                    }
                }

                else if (keyword.startsWith("car_id=")) {

                    int carID = Integer.parseInt(keyword.substring("car_id=".length()));
                    Rental rental = SQLHandlerUtil.getOneRental(transaction.getRentalID());
                    if(rental.getCarId() == carID) {

                        vboxContent.getChildren().add(hbox);
                    }
                }

                else if (keyword.startsWith("agent_id=")) {

                    int agentID = Integer.parseInt(keyword.substring("agent_id=".length()));
                    Rental rental = SQLHandlerUtil.getOneRental(transaction.getRentalID());
                    if(rental.getAgentId() == agentID) {

                        vboxContent.getChildren().add(hbox);
                    }
                }

                else if (keyword.startsWith("id=")) {
                    int transactionID = Integer.parseInt(keyword.substring("id=".length()));
                    if(transaction.getTransactionID() == transactionID) {

                        vboxContent.getChildren().add(hbox);
                    }
                }

                else if(String.valueOf(transaction.getTransactionID()).startsWith(keyword)) {

                    vboxContent.getChildren().add(hbox);
                }
            }
        }
    }

    public void setDates(LocalDate [] dates) {

        this.dates = dates;
        dateRange.setText(dates[0].toString() + " -> " + dates[1].toString());
    }

    public void setClearButton() {

        clearDateButton.setDisable(!clearDateButton.isDisabled());
    }
}
