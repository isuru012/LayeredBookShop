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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bookshop.bo.BOFactory;
import lk.ijse.bookshop.bo.custom.AdminSupplierDetailsBO;
import lk.ijse.bookshop.dto.SupplierDTO;
import lk.ijse.bookshop.util.Notification;
import lk.ijse.bookshop.view.tm.SupplierDetailTm;
import tray.notification.NotificationType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class AdminSupplierDetailsController {

    @FXML
    private AnchorPane pane2;

    @FXML
    private TableView  <SupplierDetailTm>tblSupDetails;

    @FXML
    private TableColumn<Object, Object> colName;

    @FXML
    private TableColumn<?, ?> colPhoneNumber;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colTotalOrders;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtAddess;

    @FXML
    private JFXTextField txtPhoneNumber;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    public static String SupId;
    ObservableList observableList = FXCollections.observableArrayList();

    AdminSupplierDetailsBO adminSupplierDetailsBO= (AdminSupplierDetailsBO) BOFactory.getBOFactory().getBOTypes(BOFactory.BOTypes.SUPPLIERDETAILS);

    public void initialize() throws SQLException, ClassNotFoundException {
        observableList.clear();
        Platform.runLater(() -> txtName.requestFocus());

        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTotalOrders.setCellValueFactory(new PropertyValueFactory<>("totalOrders"));

        ArrayList arrayList = adminSupplierDetailsBO.getAllDetails();
        for (Object customerTm : arrayList) {
            observableList.add(customerTm);
        }
        searchPart();
    }

    private void searchPart() {
        FilteredList<SupplierDetailTm> filteredList = new FilteredList(observableList, b -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(supplierTm -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (supplierTm.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (supplierTm.getAddress().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(supplierTm.getPhoneNumber()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<SupplierDetailTm> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tblSupDetails.comparatorProperty());
        tblSupDetails.setItems(sortedList);
    }

    @FXML
    void addOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        addSupplier();
    }

    private String generateNextSupplierId(String supplierId) {
        if (supplierId != null) {
            String[] split = supplierId.split("S");
            int id = Integer.parseInt(split[1]);

            id += 1;
            if (id>=10){
                return "S000" + id;
            }else if(id>=100){
                return "S00" +id;
            }else if(id>=1000){
                return "S0"+id;
            }else if(id>=10000){
                return "S"+id;
            }else{

                return "S0000" + id;
            }
        }
        return "S00001";
    }

    @FXML
    void deleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Supplier Data");
            alert.setContentText("Do you want to delete Supplier "+txtName.getText()+"?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                boolean deleteSupplier = adminSupplierDetailsBO.deleteSupplier(SupId);
                if (deleteSupplier) {
                    txtName.setText("");
                    txtPhoneNumber.setText("");
                    txtAddess.setText("");
                    txtSearch.setText("");
                    Notification.notifie("Supplier Data", "Supplier Data Deleted", NotificationType.INFORMATION);
                    observableList.clear();
                    initialize();
                    clearTableSelection();
                    btnAdd.setDisable(false);
                } else {
                    Notification.notifie("Supplier Data", "Supplier Data  Not Deleted", NotificationType.ERROR);
                }
            }

        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }
    }

    @FXML
    void searchOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        try{

            String name = txtName.getText();
            String address=txtAddess.getText();
            int phoneNumber = Integer.parseInt(txtPhoneNumber.getText());

            SupplierDTO supplierDTO=new SupplierDTO();
            supplierDTO.setSupplierId(SupId);
            supplierDTO.setLocation(address);
            supplierDTO.setName(name);
            supplierDTO.setPhoneNumber(phoneNumber);


            boolean updateCustomer = adminSupplierDetailsBO.updateSupplier(supplierDTO);
            if (updateCustomer) {
                Notification.notifie("Supplier Data", "Supplier Data Updated", NotificationType.INFORMATION);
                observableList.clear();
                initialize();
            } else {
                Notification.notifie("Supplier Data", "Supplier Data  Not Updated", NotificationType.ERROR);
            }
        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }

    }

    public void onMouseClickedTblSupplier(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {

        try{

            SupplierDetailTm tm = tblSupDetails.getItems().get(tblSupDetails.getSelectionModel().getSelectedIndex());
            txtName.setText(tm.getName());
            txtPhoneNumber.setText(String.valueOf(tm.getPhoneNumber()));
            txtAddess.setText(tm.getAddress());
            SupId= adminSupplierDetailsBO.getSupplierIdFromNumber(String.valueOf(colPhoneNumber.getCellData(tblSupDetails.getSelectionModel().getSelectedIndex())));

            btnAdd.setDisable(true);
        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }
    }

    public void keyReleasedName(KeyEvent keyEvent) {
        if (keyEvent.getCode()== KeyCode.ENTER){
            txtAddess.requestFocus();
        }
        clearTableSelection();
        btnAdd.setDisable(false);
    }

    private void clearTableSelection() {
        if (!tblSupDetails.isFocused()) {
            tblSupDetails.getSelectionModel().clearSelection();
        }
    }

    public void keyReleasedAddress(KeyEvent keyEvent) {
        if (keyEvent.getCode()== KeyCode.ENTER){
            txtPhoneNumber.requestFocus();
        }
        clearTableSelection();
        btnAdd.setDisable(false);
    }

    public void keyReleasedPhone(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode()== KeyCode.ENTER){
            addSupplier();
        }
        clearTableSelection();
        btnAdd.setDisable(false);
    }

    private void addSupplier() throws SQLException, ClassNotFoundException {

        try{

            String name = txtName.getText();
            int phoneNumber = Integer.parseInt(txtPhoneNumber.getText());
            String supId = generateNextSupplierId(adminSupplierDetailsBO.getSupplierId());
            String address = txtAddess.getText();
            String getUsername = adminSupplierDetailsBO.getUserName();


            SupplierDTO supplierDTO = new SupplierDTO(supId, name, address, phoneNumber, getUsername);
            boolean supplierData = adminSupplierDetailsBO.insertSupplierData(supplierDTO);
            if (supplierData) {
                Notification.notifie("Supplier Data", "Supplier Data Added", NotificationType.INFORMATION);
                observableList.clear();
                initialize();

            } else {
                Notification.notifie("Supplier Data", "Supplier Data  Not Added", NotificationType.ERROR);
            }
        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }


    }
}
