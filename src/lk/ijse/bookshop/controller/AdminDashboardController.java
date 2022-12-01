package lk.ijse.bookshop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import lk.ijse.bookshop.model.DashboardModel;
import lk.ijse.bookshop.util.WindowControll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class AdminDashboardController {

    public LineChart lineChart;
    public CategoryAxis xAxis;
    public NumberAxis yAxis;
    public Label lblOrder;
    public Label lblProduct;
    public Label lblCustomer;
    public ListView listView;

    public void initialize() throws SQLException, ClassNotFoundException {

        ArrayList arrayList=DashboardModel.getTrendingItems();
        ObservableList observableList= FXCollections.observableArrayList(arrayList);
        listView.getItems().addAll(observableList);
        returnLineChart();
        int ordersAmount = DashboardModel.getOrdersAmount();
        lblOrder.setText(String.valueOf(ordersAmount));
        int products = DashboardModel.getProductsAmount();
        lblProduct.setText(String.valueOf(products));
        int customers=DashboardModel.getCustomersAmount();
        lblCustomer.setText(String.valueOf(customers));

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
        lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        lineChart.getXAxis().setLabel("Days of the week");
        lineChart.getYAxis().setLabel("Amount  earned");
    }
}
