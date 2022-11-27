package lk.ijse.bookshop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import lk.ijse.bookshop.model.DashboardModel;
import lk.ijse.bookshop.util.WindowControll;

import java.sql.SQLException;
import java.util.Arrays;

public class AdminDashboardController {

    public LineChart lineChart;
    public CategoryAxis xAxis;
    public NumberAxis yAxis;
    public Label lblOrder;
    public Label lblProduct;
    public Label lblCustomer;

    public void initialize() throws SQLException, ClassNotFoundException {

        returnLineChart();
        int ordersAmount = DashboardModel.getOrdersAmount();
        lblOrder.setText(String.valueOf(ordersAmount));
        int products = DashboardModel.getProductsAmount();
        lblProduct.setText(String.valueOf(products));
        int customers=DashboardModel.getCustomersAmount();
        lblCustomer.setText(String.valueOf(customers));

    }

    public void returnLineChart() {
        XYChart.Series series = new XYChart.Series();


        series.getData().add(new XYChart.Data("Mon", 5000));
        series.getData().add(new XYChart.Data("Tue", 8000));
        series.getData().add(new XYChart.Data("Wed", 12000));
        series.getData().add(new XYChart.Data("Thu", 25000));
        series.getData().add(new XYChart.Data("Fri", 15000));
        series.getData().add(new XYChart.Data("Sat", 11000));
        series.getData().add(new XYChart.Data("Sun", 22000));

        lineChart.getData().addAll(series);
        lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        lineChart.getXAxis().setLabel("Days of the week");
        lineChart.getYAxis().setLabel("Amount  earned");
    }
}
