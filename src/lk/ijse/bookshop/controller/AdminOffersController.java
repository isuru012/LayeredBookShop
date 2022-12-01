package lk.ijse.bookshop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import lk.ijse.bookshop.model.CustomerModel;
import lk.ijse.bookshop.model.ItemModel;
import lk.ijse.bookshop.model.OfferModel;
import lk.ijse.bookshop.model.SupplierOrderModel;
import lk.ijse.bookshop.to.Item;
import lk.ijse.bookshop.to.Offer;
import lk.ijse.bookshop.util.Notification;
import lk.ijse.bookshop.view.tm.AdminItemTm;
import lk.ijse.bookshop.view.tm.OfferTm;
import org.controlsfx.control.textfield.TextFields;
import tray.notification.NotificationType;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class AdminOffersController {

    public TableColumn colBatchNumber;
    public JFXComboBox cmbSellingPrice;
    @FXML
    private TableView<OfferTm> tblOffer;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colStartingDate;

    @FXML
    private TableColumn<?, ?> colEndDate;

    @FXML
    private Label lblItemId;

    @FXML
    private JFXTextField txtOffer;

    @FXML
    private JFXTextField txtStartingDate;

    @FXML
    private JFXTextField txtEndingDate;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXTextField txtSellingPrice;

    static ObservableList observableList = FXCollections.observableArrayList();

    public void initialize() throws SQLException, ClassNotFoundException {

        Platform.runLater(() -> txtOffer.requestFocus());

        observableList.clear();
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colBatchNumber.setCellValueFactory(new PropertyValueFactory<>("batchNumber"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colStartingDate.setCellValueFactory(new PropertyValueFactory<>("startingDate"));
        colEndDate.setCellValueFactory(new PropertyValueFactory<>("endingDate"));

        ArrayList arrayList = OfferModel.getAllDetails();
        for (Object customerTm : arrayList) {
            observableList.add(customerTm);
        }

        searchPart();

        ArrayList loadAllItemName = OfferModel.loadAllItemNames();
        TextFields.bindAutoCompletion(txtItemName, loadAllItemName);

        txtStartingDate.setText(String.valueOf(LocalDate.now()));
        txtEndingDate.setText(String.valueOf(LocalDate.now()));
    }

    private void loadItemPrices() throws SQLException, ClassNotFoundException {
        ArrayList loadAllItemPrices = OfferModel.loadAllItemPrices(txtItemName.getText());

        cmbSellingPrice.setItems(FXCollections.observableArrayList(loadAllItemPrices));
        cmbSellingPrice.getSelectionModel().selectFirst();
    }

    private void searchPart() {
        FilteredList<OfferTm> filteredList = new FilteredList(observableList, b -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(offerTm -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (offerTm.getItemCode().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(offerTm.getEndingDate()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(offerTm.getAmount()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(offerTm.getStartingDate()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<OfferTm> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tblOffer.comparatorProperty());
        tblOffer.setItems(sortedList);
    }

    @FXML
    void addOnAction(ActionEvent event) {

        try{
            String itemCode=lblItemId.getText();

            double offerAmount = Double.parseDouble(txtOffer.getText());

            Date dateStart= Date.valueOf(txtStartingDate.getText());
            Date dateEnd= Date.valueOf(txtEndingDate.getText());

            String offerId=getNextOfferId(OfferModel.getNowOfferId());

            int batchNumber = OfferModel.getBatchNumber(Double.parseDouble(
                    String.valueOf(cmbSellingPrice.getSelectionModel().getSelectedItem())),itemCode);

            /*OfferTm offerTm=new OfferTm(itemCode,batchNumber,offerAmount,dateStart,dateEnd);*/

            Offer offer=new Offer(offerId,offerAmount,dateStart,dateEnd);

            boolean insertOfferData = OfferModel.insertOfferData(offer);

            if (insertOfferData) {
                if (OfferModel.updateItemData(itemCode, offerId, batchNumber)){
                    Notification.notifie("Offer Data", "Offer Data Added", NotificationType.INFORMATION);

                    observableList.clear();
                    initialize();
                }

            } else {
                Notification.notifie("Offer Data", "Offer Data  Not Added", NotificationType.ERROR);
            }
            clearTableSelection();
            btnAdd.setDisable(false);

        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }



    }

    private void clearTableSelection() {
        if (!tblOffer.isFocused()) {
            tblOffer.getSelectionModel().clearSelection();
        }
    }

    private String getNextOfferId(String nowOfferId) {
        if (nowOfferId != null) {
            String[] split = nowOfferId.split("F");
            int id = Integer.parseInt(split[1]);

            id += 1;
            if (id>=10){
                return "F000" + id;
            }else if(id>=100){
                return "F00" +id;
            }else if(id>=1000){
                return "F0"+id;
            }else if(id>=10000){
                return "F"+id;
            }else{

                return "F0000" + id;
            }
        }
        return "F00001";
    }

    private int getNextBatchNumber(int batchNumber) {
        return batchNumber+1;
    }

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Offer Data");
            alert.setContentText("Do you want to delete Offer "+txtItemName.getText()+"?");


            String itemId= String.valueOf(colItemCode.getCellData(tblOffer.getSelectionModel().getSelectedIndex()));
            String offerId=OfferModel.getOfferId(itemId);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                boolean deleteOffer = OfferModel.deleteOffer(itemId);

                if (deleteOffer) {
                    boolean b=OfferModel.deleteOfferData(offerId);
                    if(b) {
                        txtOffer.setText("");
                        txtItemName.setText("");

                        txtSearch.setText("");
                        cmbSellingPrice.getItems().clear();

                        Notification.notifie("Offer Data", "Offer Data Deleted", NotificationType.INFORMATION);
                        observableList.clear();
                        initialize();
                    }
                } else {
                    Notification.notifie("Offer Data", "Offer Data  Not Deleted", NotificationType.ERROR);
                }
            }
            clearTableSelection();
            btnAdd.setDisable(false);

        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }

    }

    @FXML
    void endingDateKeyReleased(KeyEvent event) {
        if (event.getCode()== KeyCode.ENTER){
            txtItemName.requestFocus();
        }
    }

    @FXML
    void offerAmountKeyReleased(KeyEvent event) {
        if (event.getCode()== KeyCode.ENTER){
            txtStartingDate.requestFocus();
        }
    }

    @FXML
    void srartingDateKeyReleased(KeyEvent event) {
        if (event.getCode()== KeyCode.ENTER){
            txtEndingDate.requestFocus();
        }
    }

    @FXML
    void tblOfferOnMouseClick(MouseEvent event) throws SQLException, ClassNotFoundException {

        try{
            txtSearch.setText("");
            OfferTm tm = tblOffer.getItems().get(tblOffer.getSelectionModel().getSelectedIndex());
            txtOffer.setText(String.valueOf(tm.getAmount()));
            txtStartingDate.setText(String.valueOf(tm.getStartingDate()));
            txtEndingDate.setText(String.valueOf(tm.getEndingDate()));
            lblItemId.setText(tm.getItemCode());

            txtItemName.setText(OfferModel.getItemName(lblItemId.getText()));
            loadItemPrices();

            btnAdd.setDisable(true);

        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }

    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        try{
            double offerAmount = Double.parseDouble(txtOffer.getText());
            Date startDate= Date.valueOf(txtStartingDate.getText());
            Date endDate= Date.valueOf(txtEndingDate.getText());

            String offerId=OfferModel.getOfferId(lblItemId.getText());

            boolean updateOffer = OfferModel.updateOffer(offerId,offerAmount,startDate,endDate);
            if (updateOffer) {
                Notification.notifie("Offer Data", "Offer Data Updated", NotificationType.INFORMATION);
                observableList.clear();
                initialize();
            } else {
                Notification.notifie("Offer Data", "Offer Data  Not Updated", NotificationType.ERROR);
            }
            clearTableSelection();
            btnAdd.setDisable(false);

        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }

    }

    public void itemNameKeyReleasedOnAction(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {

        loadItemPrices();
        lblItemId.setText(OfferModel.getItemId(txtItemName.getText()));
    }
}
