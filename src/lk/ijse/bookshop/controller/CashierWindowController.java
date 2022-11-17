package lk.ijse.bookshop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.bookshop.util.Navigation;
import lk.ijse.bookshop.util.Routes;
import lk.ijse.bookshop.util.WindowControll;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class CashierWindowController {

    ArrayList<JFXButton> arrayListButton = new ArrayList<>();
    @FXML
    private AnchorPane pane;
    @FXML
    private Label lblNameSet;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblTime;
    @FXML
    public static JFXButton btnCustomers;
    @FXML
    private JFXButton btnPlaceOrder;
    @FXML
    private JFXButton btnPlaceReload;
    @FXML
    private JFXButton btnItems;
    @FXML
    private AnchorPane pane2;
    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPhoneNumber;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    void addOnAction(ActionEvent event) {

    }

    @FXML
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void searchOnAction(ActionEvent event) {

    }

    @FXML
    void updateOnAction(ActionEvent event) {

    }

    Thread t1;
    private static boolean bool = false;

    public void initialize() {

        lblDate.setText(String.valueOf(LocalDate.now()));
        t1 = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
            while (!bool) {
                try {
                    t1.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                final String timeNow = simpleDateFormat.format(new Date());
                Platform.runLater(() -> {
                    lblTime.setText(timeNow);
                });
            }

        });
        t1.start();

        arrayListButton.add(btnCustomers);
        arrayListButton.add(btnItems);
        arrayListButton.add(btnPlaceOrder);
        arrayListButton.add(btnPlaceReload);

    }
    public void checkButton(JFXButton button) {
        for (int i = 0; i < arrayListButton.size(); i++) {
            if (arrayListButton.get(i) == button) {
                button.setTextFill(Paint.valueOf("#0aa119"));
            } else {
                arrayListButton.get(i).setTextFill(Paint.valueOf("#000000"));
            }
        }
    }

    public void checkfocus() {
        if(btnCustomers!=null) {
            if (btnCustomers.isFocused()) {
                checkButton(btnCustomers);

            } else if (btnItems.isFocused()) {
                checkButton(btnItems);
            } else if (btnPlaceOrder.isFocused()) {
                checkButton(btnPlaceOrder);
            } else if (btnPlaceReload.isFocused()) {
                checkButton(btnPlaceReload);
            }
        }
    }

    @FXML
    void customersOnAction(ActionEvent event) throws IOException {
        checkfocus();
        Navigation.navigate(Routes.CASHIERCUSTOMER, pane2);

    }

    @FXML
    void itemsOnAction(ActionEvent event) throws IOException {
        checkfocus();
        Navigation.navigate(Routes.CASHIERITEMS,pane2);

    }

    @FXML
    void placeOrderOnAction(ActionEvent event) throws IOException {
        checkfocus();
        Navigation.navigate(Routes.CASHIERPLACEORDER,pane2);
    }

    @FXML
    void placeReloadOnAction(ActionEvent event) throws IOException {
        checkfocus();
        Navigation.navigate(Routes.CASHIERMAKERELOAD,pane2);
    }

    public void closeOnAction(ActionEvent actionEvent) {
        t1.stop();
        WindowControll.window(null, actionEvent);
    }

    public void minimizeOnAction(ActionEvent actionEvent) {
        WindowControll.window(pane, actionEvent);
    }





}
