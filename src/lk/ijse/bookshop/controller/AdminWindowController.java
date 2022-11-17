package lk.ijse.bookshop.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.bookshop.util.Navigation;
import lk.ijse.bookshop.util.Routes;
import lk.ijse.bookshop.util.WindowControll;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class AdminWindowController {
    @FXML
    public AnchorPane pane;
    @FXML
    public AnchorPane pane2;
    @FXML
    public JFXButton btnDashboard;
    @FXML
    public JFXButton btnEmployee;
    @FXML
    public Label lblDate;
    @FXML
    public Label lblTime;
    @FXML
    Thread t1;
    @FXML
    private JFXButton btnSupplier;

    @FXML
    private JFXButton btnOffers;

    @FXML
    private JFXButton btnPayments;

    @FXML
    private JFXButton btnExpenditures;
    private static boolean bool = false;
    ArrayList<JFXButton> arrayListButton=new ArrayList<>();


    public void initialize()  {


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
        arrayListButton.add(btnDashboard);
        arrayListButton.add(btnEmployee);
        arrayListButton.add(btnExpenditures);
        arrayListButton.add(btnOffers);
        arrayListButton.add(btnPayments);
        arrayListButton.add(btnSupplier);

    }


    public void checkButton(JFXButton button){
        for (int i = 0; i <arrayListButton.size(); i++) {
            if (arrayListButton.get(i)==button){
                button.setTextFill(Paint.valueOf("#0aa119"));
            }else{
                arrayListButton.get(i).setTextFill(Paint.valueOf("#000000"));
            }
        }
    }

    public void checkfocus() {

        if (btnDashboard.isFocused()) {
             checkButton(btnDashboard);

        } else if (btnEmployee.isFocused()) {
            checkButton(btnEmployee);
        }else if (btnSupplier.isFocused()){
           checkButton(btnSupplier);
        }else if (btnPayments.isFocused()){
            checkButton(btnPayments);
        }else if (btnOffers.isFocused()){
           checkButton(btnOffers);

        }else if (btnExpenditures.isFocused()){
            checkButton(btnExpenditures);
        }
    }

    public void closeOnAction(ActionEvent actionEvent) {
        WindowControll.window(null, actionEvent);
        bool = true;
    }

    public void minimizeOnAction(ActionEvent actionEvent) {
        WindowControll.window(pane, actionEvent);
    }

    public void dashboradOnAction(ActionEvent actionEvent) throws IOException {
        checkfocus();

        Navigation.navigate(Routes.DASHBOARD, pane2);
    }


    public void mousepressd(MouseEvent mouseEvent) {
    }

    public void employeeOnAction(ActionEvent actionEvent) throws IOException {
        checkfocus();
        Navigation.navigate(Routes.EMPLOYEE,pane2);
    }

    public void supplierOnAction(ActionEvent actionEvent) throws IOException {
        checkfocus();
        Navigation.navigate(Routes.SUPPLIERORDER,pane2);
    }

    public void offerOnAction(ActionEvent actionEvent) throws IOException {
        checkfocus();
        Navigation.navigate(Routes.OFFERS,pane2);
    }

    public void paymentOnAction(ActionEvent actionEvent) throws IOException {
        checkfocus();
        Navigation.navigate(Routes.PAYMENT,pane2);
    }

    public void expenditureOnAction(ActionEvent actionEvent) throws IOException {
        checkfocus();
        Navigation.navigate(Routes.EXPENDITURE,pane2);
    }
}
