package main.controllers.views;

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
import main.objects.Account;
import main.objects.Rental;
import main.objects.RentalTransaction;
import main.util.SQLHandlerUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    private VBox vboxContent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            initializeTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    void initializeTable() throws SQLException {

        vboxContent.getChildren().clear();
        vboxContent.setAlignment(Pos.TOP_CENTER);

        try {
            for (RentalTransaction transaction : SQLHandlerUtil.getRentalTransactions(Account.getUserID())) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitTransaction.fxml"));
                HBox hbox = loader.load();
                ViewTransactionController transactionController = loader.getController();
                transactionController.setData(transaction.getTransactionID(), transaction.getRentalID(), transaction.getDate(), transaction.getAmount(), transaction.getTransactionName());
                vboxContent.getChildren().add(hbox);
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void search(KeyEvent event) throws SQLException {

        String keyword = ((TextField)event.getSource()).getText().toLowerCase();
        vboxContent.getChildren().clear();

        for (RentalTransaction transaction : SQLHandlerUtil.getRentalTransactions(Account.getUserID())) {

            if (keyword.startsWith("name=")) {

                String name = keyword.substring("name=".length());
                if(transaction.getTransactionName().toLowerCase().equals(name)) {

                    try{

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitTransaction.fxml"));
                        HBox hbox = loader.load();
                        ViewTransactionController transactionController = loader.getController();
                        transactionController.setData(transaction.getTransactionID(), transaction.getRentalID(), transaction.getDate(), transaction.getAmount(), transaction.getTransactionName());
                        vboxContent.getChildren().add(hbox);
                    }

                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            else if (keyword.startsWith("date=")) {

                String date = keyword.substring("date=".length());
                if(transaction.getDate().toLowerCase().equals(date)) {

                    try{

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitTransaction.fxml"));
                        HBox hbox = loader.load();
                        ViewTransactionController transactionController = loader.getController();
                        transactionController.setData(transaction.getTransactionID(), transaction.getRentalID(), transaction.getDate(), transaction.getAmount(), transaction.getTransactionName());
                        vboxContent.getChildren().add(hbox);
                    }

                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            else if (keyword.startsWith("amount=")) {

                double amount = Double.parseDouble(keyword.substring("amount=".length()));
                if(transaction.getAmount() == amount) {

                    try{

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitTransaction.fxml"));
                        HBox hbox = loader.load();
                        ViewTransactionController transactionController = loader.getController();
                        transactionController.setData(transaction.getTransactionID(), transaction.getRentalID(), transaction.getDate(), transaction.getAmount(), transaction.getTransactionName());
                        vboxContent.getChildren().add(hbox);
                    }

                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            else if (keyword.startsWith("rental_id=")) {

                int rentalID = Integer.parseInt(keyword.substring("rental_id=".length()));
                if(transaction.getRentalID() == rentalID) {

                    try{

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitTransaction.fxml"));
                        HBox hbox = loader.load();
                        ViewTransactionController transactionController = loader.getController();
                        transactionController.setData(transaction.getTransactionID(), transaction.getRentalID(), transaction.getDate(), transaction.getAmount(), transaction.getTransactionName());
                        vboxContent.getChildren().add(hbox);
                    }

                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            else if (keyword.startsWith("car_id=")) {

                int carID = Integer.parseInt(keyword.substring("car_id=".length()));
                Rental rental = SQLHandlerUtil.getOneRental(transaction.getRentalID());
                if(rental.getCarId() == carID) {

                    try{

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitTransaction.fxml"));
                        HBox hbox = loader.load();
                        ViewTransactionController transactionController = loader.getController();
                        transactionController.setData(transaction.getTransactionID(), transaction.getRentalID(), transaction.getDate(), transaction.getAmount(), transaction.getTransactionName());
                        vboxContent.getChildren().add(hbox);
                    }

                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            else if (keyword.startsWith("agent_id=")) {

                int agentID = Integer.parseInt(keyword.substring("agent_id=".length()));
                Rental rental = SQLHandlerUtil.getOneRental(transaction.getRentalID());
                if(rental.getAgentId() == agentID) {

                    try{

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitTransaction.fxml"));
                        HBox hbox = loader.load();
                        ViewTransactionController transactionController = loader.getController();
                        transactionController.setData(transaction.getTransactionID(), transaction.getRentalID(), transaction.getDate(), transaction.getAmount(), transaction.getTransactionName());
                        vboxContent.getChildren().add(hbox);
                    }

                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            else if (keyword.startsWith("id=")) {
                int transactionID = Integer.parseInt(keyword.substring("id=".length()));
                if(transaction.getTransactionID() == transactionID) {
                    try{

                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitTransaction.fxml"));
                        HBox hbox = loader.load();
                        ViewTransactionController transactionController = loader.getController();
                        transactionController.setData(transaction.getTransactionID(), transaction.getRentalID(), transaction.getDate(), transaction.getAmount(), transaction.getTransactionName());
                        vboxContent.getChildren().add(hbox);
                    }

                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            else if(String.valueOf(transaction.getTransactionID()).startsWith(keyword)) {

                try{

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/unitTransaction.fxml"));
                    HBox hbox = loader.load();
                    ViewTransactionController transactionController = loader.getController();
                    transactionController.setData(transaction.getTransactionID(), transaction.getRentalID(), transaction.getDate(), transaction.getAmount(), transaction.getTransactionName());
                    vboxContent.getChildren().add(hbox);
                }

                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
