package lk.ijse.bookshop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import lk.ijse.bookshop.util.WindowControll;

import java.util.Arrays;

public class AdminDashboardController {

    public LineChart lineChart;
    public CategoryAxis xAxis;
    public NumberAxis yAxis;

    public void initialize(){
        /*xAxis = new CategoryAxis();
        xAxis.setCategories(FXCollections.<String>observableArrayList
                (Arrays.asList("Mon", "Tue","Wed","Thu","Fri","Sat","Sun")));
        xAxis.setLabel("Days");
        yAxis = new NumberAxis(0, 35000, 5000);
        yAxis.setLabel("Amount Earned");
        LineChart linechart = new LineChart(xAxis, yAxis);*/
        returnLineChart();


    }
    public void returnLineChart(){
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
