package lk.ijse.bookshop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bookshop.model.CustomerModel;
import lk.ijse.bookshop.model.ItemModel;
import lk.ijse.bookshop.to.Item;
import lk.ijse.bookshop.util.Notification;
import lk.ijse.bookshop.view.tm.AdminItemTm;
import lk.ijse.bookshop.view.tm.CashierItemTm;
import lk.ijse.bookshop.view.tm.CustomerTm;
import tray.notification.NotificationType;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Optional;

public class AdminItemsController {

    @FXML
    private AnchorPane pane2;

    @FXML
    private JFXTextField txtDesCription;

    @FXML
    private JFXTextField txtBuyingPrice;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXTextField txtSellingPrice;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private TableView<AdminItemTm> tblItem;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colBatchNumber;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colBuyingPrice;

    @FXML
    private TableColumn<?, ?> colSellingPrice;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colOfferAmount;

    @FXML
    private Label lblCode;

    @FXML
    private Label lblBatchNumber;
    static ObservableList observableList = FXCollections.observableArrayList();

    public void initialize() throws SQLException, ClassNotFoundException {
        observableList.clear();
        Platform.runLater(() -> txtDesCription.requestFocus());

        lblCode.setText(getNextItemId(ItemModel.getCurrentItemId()));

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colBatchNumber.setCellValueFactory(new PropertyValueFactory<>("batchNumber"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colBuyingPrice.setCellValueFactory(new PropertyValueFactory<>("buyingUnitPrice"));
        colSellingPrice.setCellValueFactory(new PropertyValueFactory<>("sellingUnitPrice"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantityOnHand"));
        colOfferAmount.setCellValueFactory(new PropertyValueFactory<>("offerAmount"));

        ArrayList arrayList = ItemModel.getAllDetailsForAdminItem();
        for (Object customerTm : arrayList) {
            observableList.add(customerTm);
        }
        searchPart();
    }

    private String getNextItemId(String currentItemId) {
        if (currentItemId != null) {
            String[] split = currentItemId.split("I");
            int id = Integer.parseInt(split[1]);

            id += 1;
            if (id>=10){
                return "I000" + id;
            }else if(id>=100){
                return "I00" +id;
            }else if(id>=1000){
                return "I0"+id;
            }else if(id>=10000){
                return "I"+id;
            }else{

                return "I0000" + id;
            }
        }
        return "I00001";
    }

    private void searchPart() {
        FilteredList<AdminItemTm> filteredList = new FilteredList(observableList, b -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(adminItemTm -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (adminItemTm.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (adminItemTm.getItemId().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(adminItemTm.getQuantityOnHand()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<AdminItemTm> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tblItem.comparatorProperty());
        tblItem.setItems(sortedList);
    }

    @FXML
    void addOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        addToItemTable();

    }

    @FXML
    void buyingPriceKeyReleasedOnAction(KeyEvent event) {
        if (event.getCode()== KeyCode.ENTER){
            txtSellingPrice.requestFocus();
        }

        btnAdd.setDisable(false);
    }

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Item Data");
        alert.setContentText("Do you want to delete Item "+txtDesCription.getText()+"?");
        String itemId= String.valueOf(colItemCode.getCellData(tblItem.getSelectionModel().getSelectedIndex()));
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            boolean deleteItem = ItemModel.deleteItem(itemId);
            if (deleteItem) {
                txtDesCription.setText("");
                txtQty.setText("");
                txtSellingPrice.setText("");
                txtSearch.setText("");
                txtBuyingPrice.setText("");

                Notification.notifie("Item Data", "Item Data Deleted", NotificationType.INFORMATION);
                observableList.clear();
                lblBatchNumber.setText("1");
                initialize();
            } else {
                Notification.notifie("Item Data", "Item Data  Not Deleted", NotificationType.ERROR);
            }
        }
        clearTableSelection();
        btnAdd.setDisable(false);

    }

    @FXML
    void descriptionKeyReleasedOnAction(KeyEvent event) {
        if (event.getCode()== KeyCode.ENTER){
            txtBuyingPrice.requestFocus();
        }
        btnAdd.setDisable(false);
    }

    @FXML
    void quantityKeyReleasedOnAction(KeyEvent event) throws SQLException, ClassNotFoundException {
        if (event.getCode()== KeyCode.ENTER){
            addToItemTable();
            clearFields();
            txtDesCription.requestFocus();
        }
        btnAdd.setDisable(false);

    }

    private void addToItemTable() throws SQLException, ClassNotFoundException {

        try{
            String itemCode=lblCode.getText();
            int batchNumber = Integer.parseInt(lblBatchNumber.getText());
            String description =txtDesCription.getText();
            double buyingPrice= Double.parseDouble(txtBuyingPrice.getText());
            double sellinPrice= Double.parseDouble(txtSellingPrice.getText());
            int quantity= Integer.parseInt(txtQty.getText());

            AdminItemTm itemTm=new AdminItemTm(itemCode,batchNumber,description,buyingPrice,sellinPrice,quantity,0);

            Item item=new Item(itemCode,batchNumber,description,buyingPrice,sellinPrice,quantity,null);

            boolean insertItemData = ItemModel.insertItemData(item);

            if (insertItemData) {
                Notification.notifie("Item Data", "Item Data Added", NotificationType.INFORMATION);
                lblCode.setText(getNextItemId(ItemModel.getCurrentItemId()));
                observableList.add(itemTm);
                searchPart();
            } else {
                Notification.notifie("Item Data", "Item Data  Not Added", NotificationType.ERROR);
            }
            clearTableSelection();
            btnAdd.setDisable(false);

        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }

    }

    @FXML
    void sellingPriceKeyReleasedOnAction(KeyEvent event) {
        if (event.getCode()== KeyCode.ENTER){
            txtQty.requestFocus();
        }
        btnAdd.setDisable(false);

    }

    @FXML
    void tblItemOnActionMouseClick(MouseEvent event) {

        try{
            AdminItemTm tm = tblItem.getItems().get(tblItem.getSelectionModel().getSelectedIndex());
            txtDesCription.setText(tm.getDescription());
            txtBuyingPrice.setText(String.valueOf(tm.getBuyingUnitPrice()));
            txtSellingPrice.setText(String.valueOf(tm.getSellingUnitPrice()));
            txtQty.setText(String.valueOf(tm.getQuantityOnHand()));
            lblCode.setText(String.valueOf(colItemCode.getCellData(tblItem.getSelectionModel().getSelectedIndex())));
            lblBatchNumber.setText(String.valueOf(colBatchNumber.getCellData(tblItem.getSelectionModel().getSelectedIndex())));
            btnAdd.setDisable(true);

        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        try{
            String desCriptionText = txtDesCription.getText();
            double buyingPrice= Double.parseDouble(txtBuyingPrice.getText());
            double sellingPrice= Double.parseDouble(txtSellingPrice.getText());
            int quantity= Integer.parseInt(txtQty.getText());
            int batchNumber=Integer.parseInt(String.valueOf(colBatchNumber.getCellData(tblItem.getSelectionModel().getSelectedIndex())));
            String itemCode=String.valueOf(colItemCode.getCellData(tblItem.getSelectionModel().getSelectedIndex()));



            boolean updateItem = ItemModel.updateItem(itemCode,batchNumber,desCriptionText,buyingPrice,sellingPrice,quantity);
            if (updateItem) {
                Notification.notifie("Item Data", "Item Data Updated", NotificationType.INFORMATION);
                observableList.clear();
                initialize();
            } else {
                Notification.notifie("Item Data", "Item Data  Not Updated", NotificationType.ERROR);
            }
            clearTableSelection();
            btnAdd.setDisable(false);

        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }
    }

    private void clearTableSelection() {
        if (!tblItem.isFocused()) {
            tblItem.getSelectionModel().clearSelection();
        }
    }

    public void onMouseClickRefresh(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {

        try{

            clearFields();
            clearTableSelection();
            lblCode.setText(getNextItemId(ItemModel.getCurrentItemId()));
            lblBatchNumber.setText("1");

            btnAdd.setDisable(false);
        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }
    }

    private void clearFields() {
        txtSellingPrice.setText("");
        txtBuyingPrice.setText("");
        txtSearch.setText("");
        txtQty.setText("");
        txtDesCription.setText("");

    }
}
