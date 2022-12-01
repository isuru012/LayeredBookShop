package lk.ijse.bookshop.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.bookshop.model.DashboardModel;
import lk.ijse.bookshop.util.Navigation;
import lk.ijse.bookshop.util.Routes;
import lk.ijse.bookshop.util.WindowControll;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
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
    public JFXButton btnItems;
    public LineChart lineChart;
    public Label lblProduct;
    public Label lblCustomer;
    public Label lblOrder;
    public ListView listView;
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
    ArrayList<JFXButton> arrayListButton = new ArrayList<>();


    public void initialize() throws SQLException, ClassNotFoundException {
        ArrayList arrayList=DashboardModel.getTrendingItems();
        ObservableList observableList= FXCollections.observableArrayList(arrayList);
        listView.getItems().addAll(observableList);
        returnLineChart();
        btnDashboard.setTextFill(Paint.valueOf("#0aa119"));
        btnDashboard.setStyle("-fx-background-color: #dcf6dd");
        lblDate.setText(String.valueOf(LocalDate.now()));
        t1 = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss a");
            while (!bool) {
                try {
                    t1.sleep(10);
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

        int ordersAmount = DashboardModel.getOrdersAmount();
        lblOrder.setText(String.valueOf(ordersAmount));
        int products = DashboardModel.getProductsAmount();
        lblProduct.setText(String.valueOf(products));
        int customers=DashboardModel.getCustomersAmount();
        lblCustomer.setText(String.valueOf(customers));



        arrayListButton.add(btnDashboard);
        arrayListButton.add(btnEmployee);
        arrayListButton.add(btnSupplier);
        arrayListButton.add(btnItems);
        arrayListButton.add(btnOffers);
        arrayListButton.add(btnPayments);
        arrayListButton.add(btnExpenditures);





    }
    public void returnLineChart() throws SQLException, ClassNotFoundException {
        XYChart.Series series = new XYChart.Series();
        double monday=DashboardModel.getMonday();
        double tuesday=DashboardModel.getTuesday();
        double wednesday=DashboardModel.getWednesday();
        double thursday=DashboardModel.getThursday();
        double friday=DashboardModel.getFriday();
        double saturday=DashboardModel.getSaturday();
        double sunday=DashboardModel.getSunday();


        series.getData().add(new XYChart.Data("Mon", monday));
        series.getData().add(new XYChart.Data("Tue", tuesday));
        series.getData().add(new XYChart.Data("Wed", wednesday));
        series.getData().add(new XYChart.Data("Thu", thursday));
        series.getData().add(new XYChart.Data("Fri", friday));
        series.getData().add(new XYChart.Data("Sat", saturday));
        series.getData().add(new XYChart.Data("Sun", sunday));

        lineChart.getData().addAll(series);
        lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: WHITE;");
        lineChart.getXAxis().setLabel("Days of the week");

        lineChart.getYAxis().setLabel("Amount  earned");
    }


    public void checkButton(JFXButton button) {
        for (int i = 0; i < arrayListButton.size(); i++) {
            if (arrayListButton.get(i) == button) {
                button.setTextFill(Paint.valueOf("#0aa119"));
            } else if(i==1){
                arrayListButton.get(i).setTextFill(Paint.valueOf("#000000"));
                btnEmployee.setStyle(null);
            }else if(i==2){
                arrayListButton.get(i).setTextFill(Paint.valueOf("#000000"));
                btnSupplier.setStyle(null);
            }else if(i==3){
                arrayListButton.get(i).setTextFill(Paint.valueOf("#000000"));
                btnItems.setStyle(null);
            }else if(i==4){
                arrayListButton.get(i).setTextFill(Paint.valueOf("#000000"));
                btnOffers.setStyle(null);
            }else if(i==6){
                arrayListButton.get(i).setTextFill(Paint.valueOf("#000000"));
                btnExpenditures.setStyle(null);
            }

            else {
                arrayListButton.get(i).setTextFill(Paint.valueOf("#000000"));
                btnDashboard.setStyle(null);
            }
        }
    }

    public void checkfocus() {

        if (btnDashboard.isFocused()) {
            checkButton(btnDashboard);

        } else if (btnEmployee.isFocused()) {
            btnEmployee.setStyle("-fx-background-color: #dcf6dd;-fx-border-color: #0aa119;-fx-border-width: 0px 0px 0px 6px;");
            checkButton(btnEmployee);
        } else if (btnSupplier.isFocused()) {
            btnSupplier.setStyle("-fx-background-color: #dcf6dd;-fx-border-color: #0aa119;-fx-border-width: 0px 0px 0px 6px;");
            checkButton(btnSupplier);
        } else if (btnItems.isFocused()) {
            btnItems.setStyle("-fx-background-color: #dcf6dd;-fx-border-color: #0aa119;-fx-border-width: 0px 0px 0px 6px;");
            checkButton(btnItems);
        } else if (btnPayments.isFocused()) {
            checkButton(btnPayments);
        } else if (btnOffers.isFocused()) {
            btnOffers.setStyle("-fx-background-color: #dcf6dd;-fx-border-color: #0aa119;-fx-border-width: 0px 0px 0px 6px;");
            checkButton(btnOffers);

        } else if (btnExpenditures.isFocused()) {
            btnExpenditures.setStyle("-fx-background-color: #dcf6dd;-fx-border-color: #0aa119;-fx-border-width: 0px 0px 0px 6px;");
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
        Navigation.navigate(Routes.EMPLOYEE, pane2);
    }

    public void supplierOnAction(ActionEvent actionEvent) throws IOException {
        checkfocus();

        Navigation.navigate(Routes.SUPPLIERORDER, pane2);
    }

    public void offerOnAction(ActionEvent actionEvent) throws IOException {
        checkfocus();
        Navigation.navigate(Routes.OFFERS, pane2);
    }

    public void paymentOnAction(ActionEvent actionEvent) throws IOException {
        checkfocus();
        Navigation.navigate(Routes.PAYMENT, pane2);
    }

    public void expenditureOnAction(ActionEvent actionEvent) throws IOException {
        checkfocus();
        Navigation.navigate(Routes.EXPENDITURE, pane2);
    }

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN, pane);
    }

    public void itemsOnAction(ActionEvent actionEvent) throws IOException {
        checkfocus();
        Navigation.navigate(Routes.ADMINITEMS, pane2);
    }
}
