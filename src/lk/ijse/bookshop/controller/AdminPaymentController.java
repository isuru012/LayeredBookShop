package lk.ijse.bookshop.controller;


import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bookshop.bo.BOFactory;
import lk.ijse.bookshop.bo.custom.AdminPaymentBO;
import lk.ijse.bookshop.view.tm.CustomerTm;
import lk.ijse.bookshop.view.tm.PaymentTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminPaymentController {

    static ObservableList observableList = FXCollections.observableArrayList();
    public TableView <PaymentTm>tblPayment;
    public TableColumn colCode;
    public TableColumn colAmount;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colSupplierName;
    public TableColumn colExpenditure;
    public JFXTextField txtSearch;
    public JFXComboBox cmbSort;

    public void initialize() throws SQLException, ClassNotFoundException {

        AdminPaymentBO adminPaymentBO= (AdminPaymentBO) BOFactory.getBOFactory().getBOTypes(BOFactory.BOTypes.ADMINPAYMENT);

        observableList.clear();
        colCode.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colExpenditure.setCellValueFactory(new PropertyValueFactory<>("expenditure"));

        ArrayList arrayList = adminPaymentBO.getAllDetails();
        for (Object payment : arrayList) {
            observableList.add(payment);
        }


        searchPart();
    }

    private void searchPart() {
        FilteredList<PaymentTm> filteredList = new FilteredList(observableList, b -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(paymentTm -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (paymentTm.getPaymentId().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(paymentTm.getDate()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(paymentTm.getAmount()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<PaymentTm> sortedList = new SortedList(filteredList);
        sortedList.comparatorProperty().bind(tblPayment.comparatorProperty());
        tblPayment.setItems(sortedList);
    }

    @FXML
    void searchOnAction(ActionEvent event) {

    }

}
