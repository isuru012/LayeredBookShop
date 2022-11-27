package lk.ijse.bookshop.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bookshop.model.CustomerModel;
import lk.ijse.bookshop.model.ItemModel;
import lk.ijse.bookshop.view.tm.CustomerTm;
import lk.ijse.bookshop.view.tm.ItemTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class CashierItemsController {

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private TableView tblItem;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colBatchNumber;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colSellingPrice;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colOffer;

    static ObservableList observableList= FXCollections.observableArrayList();

    public void initialize() throws SQLException, ClassNotFoundException {
        observableList.clear();
        colCode.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colBatchNumber.setCellValueFactory(new PropertyValueFactory<>("batchNumber"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colSellingPrice.setCellValueFactory(new PropertyValueFactory<>("sellingUnitPrice"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantityOnHand"));
        colOffer.setCellValueFactory(new PropertyValueFactory<>("offerId"));

        ArrayList arrayList = ItemModel.getAllDetails();
        for (Object customerTm : arrayList) {
            observableList.add(customerTm);
        }
        searchPart();
    }

    private void searchPart() {
        FilteredList<ItemTm> filteredList = new FilteredList(observableList, b -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(itemTm -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (itemTm.getItemId().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (itemTm.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(itemTm.getSellingUnitPrice()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<CustomerTm> sortedList = new SortedList(filteredList);
        sortedList.comparatorProperty().bind(tblItem.comparatorProperty());
        tblItem.setItems(sortedList);
    }

}
