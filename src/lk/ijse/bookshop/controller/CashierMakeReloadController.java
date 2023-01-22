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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.bookshop.bo.BOFactory;
import lk.ijse.bookshop.bo.custom.CashierMakeReloadBO;
import lk.ijse.bookshop.dto.*;
import lk.ijse.bookshop.util.Notification;
import lk.ijse.bookshop.view.tm.CustomerTm;
import lk.ijse.bookshop.view.tm.ReloadTm;
import org.controlsfx.control.textfield.TextFields;
import tray.notification.NotificationType;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class CashierMakeReloadController {

    public JFXButton btnPlaceOrder;
    public JFXTextField txtDiscount;
    public JFXTextField txtCash;
    public JFXTextField txtAmount;
    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblIReloadCode;


    @FXML
    private TableView  <CustomerTm>tblCustomer;

    @FXML
    private TableColumn<?, ?> colCode;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhoneNumber;

    @FXML
    private TableColumn<?, ?> colJoinedDate;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPhoneNumber;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView <ReloadTm>  tblOrder;

    @FXML
    private TableColumn<?, ?> colReloadCode;

    @FXML
    private TableColumn<?, ?> colDescription;


    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private JFXTextField txtDescription;


    @FXML
    private Label lblBalance;

    static ObservableList observableList = CashierCustomersController.observableList;
    static String CusId = CashierCustomersController.CusId;

    CashierMakeReloadBO cashierMakeReloadBO= (CashierMakeReloadBO) BOFactory.getBOFactory().
            getBOTypes(BOFactory.BOTypes.CASHIERMAKERELOAD);

    public void initialize() throws SQLException, ClassNotFoundException {
        Platform.runLater(() -> txtSearch.requestFocus());
        btnPlaceOrder.setDisable(true);
        observableList.clear();
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colJoinedDate.setCellValueFactory(new PropertyValueFactory<>("joinedDate"));

        ArrayList arrayList = cashierMakeReloadBO.getAllDetails();
        for (Object customerTm : arrayList) {
            observableList.add(customerTm);
        }

        searchPart();

        lblOrderDate.setText(String.valueOf(LocalDate.now()));
        lblOrderId.setText(generateNextReloadId(cashierMakeReloadBO.getOrderId()));
        ArrayList loadAllServiceProviders = cashierMakeReloadBO.loadAllServiceProviders();
        TextFields.bindAutoCompletion(txtDescription, loadAllServiceProviders);

        colReloadCode.setCellValueFactory(new PropertyValueFactory<>("reloadCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("serviceProvider"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

    }

    private String generateNextReloadId(String orderId) {
        if (orderId != null) {
            String[] split = orderId.split("U");
            int id = Integer.parseInt(split[1]);

            id += 1;
            if (id>=10){
                return "U000" + id;
            }else if(id>=100){
                return "U00" +id;
            }else if(id>=1000){
                return "U0"+id;
            }else if(id>=10000){
                return "U"+id;
            }else{

                return "U0000" + id;
            }
        }
        return "U00001";
    }

    private void searchPart() {
        FilteredList<CustomerTm> filteredList = new FilteredList(observableList, b -> true);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(customerTm -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (customerTm.getCode().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (customerTm.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(customerTm.getPhoneNumber()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });
        });

        SortedList<CustomerTm> sortedList = new SortedList(filteredList);
        sortedList.comparatorProperty().bind(tblCustomer.comparatorProperty());
        tblCustomer.setItems(sortedList);
    }

    @FXML
    void addCustomerOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        try{
            if (!txtName.getText().equals("") && !txtPhoneNumber.getText().equals("")) {
                String name = txtName.getText();
                int phoneNumber = Integer.parseInt(txtPhoneNumber.getText());
                String cusId = generateNextCustomeId(cashierMakeReloadBO.getCusId());
                /*LocalDate date= LocalDate.now();*/
                java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
                String employeeId = LoginFormController.employeeId;

                CustomerDTO customerDTO = new CustomerDTO(cusId, name, phoneNumber, date, employeeId);
                boolean customerData = cashierMakeReloadBO.insertCustomerData(customerDTO);
                if (customerData) {
                    Notification.notifie("Customer Data", "Customer Data Added", NotificationType.INFORMATION);
                } else {
                    Notification.notifie("Customer Data", "Customer Data  Not Added", NotificationType.ERROR);
                }

                CustomerTm customerTm = new CustomerTm(cusId, name, phoneNumber, date);

                observableList.add(customerTm);
                searchPart();
            }

        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }

    }
    private String generateNextCustomeId(String orderId) {
        if (orderId != null) {
            String[] split = orderId.split("C");
            int id = Integer.parseInt(split[1]);

            id += 1;
            if (id>=10){
                return "C000" + id;
            }else if(id>=100){
                return "C00" +id;
            }else if(id>=1000){
                return "C0"+id;
            }else if(id>=10000){
                return "C"+id;
            }else{

                return "C0000" + id;
            }
        }
        return "C00001";
    }

    ObservableList<ReloadTm> observableList1 = FXCollections.observableArrayList();
    @FXML
    void addToCartOnAction(ActionEvent event) {
        addToTableOrder();
    }

    private void addToTableOrder() {

        try{
            String code = lblIReloadCode.getText();
            String description = txtDescription.getText();
            double amount = Integer.parseInt(txtAmount.getText());

            ReloadTm reloadTm=new ReloadTm(code,description,amount);
            for (int i = 0; i < tblOrder.getItems().size(); i++) {
                if (colReloadCode.getCellData(i).equals(lblIReloadCode.getText())) {

                    double tempTotal = observableList1.get(i).getTotalAmount() + amount;


                    observableList1.get(i).setTotalAmount(tempTotal);

                    tblOrder.refresh();
                    generateTotal();
                    return;
                }

            }


            observableList1.add(reloadTm);
            tblOrder.setItems(observableList1);
            generateTotal();

        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }
    }

    private void generateTotal() {
        double total = 0;
        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            total += Double.parseDouble(String.valueOf(colTotal.getCellData(i)));

        }
        lblTotal.setText(String.valueOf(total));
    }

    @FXML
    void addToCartOnEnterKey(KeyEvent event) {

        try{

            if (event.getCode() == KeyCode.ENTER) {
                addToTableOrder();

            }
        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }
    }

    @FXML
    void cashOnReleased(KeyEvent event) {

        try{
            if (!txtCash.getText().equals("")) {
                double discount;
                btnPlaceOrder.setDisable(true);
                if (txtDiscount.getText().equals("")) {
                    discount = 0;
                } else {

                    discount = Double.parseDouble(txtDiscount.getText()) / 100 * Double.parseDouble(lblTotal.getText());
                }

                String balance = String.valueOf(Double.parseDouble(txtCash.getText()) -
                        (Double.parseDouble(lblTotal.getText()) - discount));
                lblBalance.setText(balance);
                if (Double.parseDouble(lblBalance.getText()) >= 0) {
                    btnPlaceOrder.setDisable(false);
                }
            }

        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) {

        try{
            tblOrder.getItems().removeAll(tblOrder.getSelectionModel().getSelectedItems());
            generateTotal();

        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }
    }

    @FXML
    void discountOnReleased(KeyEvent event) {

        try{

            if (!txtCash.getText().equals("")) {
                double discount;
                btnPlaceOrder.setDisable(true);
                if (txtDiscount.getText().equals("")) {
                    discount = 0;
                } else {

                    discount = Double.parseDouble(txtDiscount.getText()) / 100 * Double.parseDouble(lblTotal.getText());
                }

                String balance = String.valueOf(Double.parseDouble(txtCash.getText()) -
                        (Double.parseDouble(lblTotal.getText()) - discount));
                lblBalance.setText(balance);
                if (Double.parseDouble(lblBalance.getText()) >= 0) {
                    btnPlaceOrder.setDisable(false);
                }
            }
        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }
    }

    @FXML
    void keyReleasedOnAction(KeyEvent event) throws SQLException, ClassNotFoundException {

        try{

            String text = txtDescription.getText();
            ReloadDTO reload = cashierMakeReloadBO.searchDescription(text);
            if (reload != null) {
                lblIReloadCode.setText(reload.getReloadId());

            }

            if (event.getCode() == KeyCode.ENTER) {
                txtAmount.requestFocus();
            }
        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }
        }


    @FXML
    void onMouseClickRefresh(MouseEvent event) {

        try{
            clearFields();
            btnPlaceOrder.setDisable(false);
            btnAdd.setDisable(false);

        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }
    }

    private void clearFields() {
        txtName.setText("");
        txtPhoneNumber.setText("");
        txtSearch.setText("");
        txtCash.setText("");
        txtAmount.setText("");
        txtDescription.setText("");
        txtDiscount.setText("");
        lblTotal.setText("");
        lblBalance.setText("");
        lblIReloadCode.setText("");
        lblCustomerName.setText("");
        tblOrder.getItems().clear();
    }

    @FXML
    void updateCartOnAction(ActionEvent event) {

        try{
            double amount = Integer.parseInt(txtAmount.getText());

            observableList1.get(tblOrder.getSelectionModel().getSelectedIndex()).setTotalAmount(amount);
            tblOrder.refresh();
            generateTotal();

        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }
    }

    @FXML
    void updateCustomerOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        try{

            if (tblCustomer.getSelectionModel().getSelectedIndex() != -1) {
                String name = txtName.getText();
                int phoneNumber = Integer.parseInt(txtPhoneNumber.getText());

                CustomerDTO customerDTO=new CustomerDTO();
                customerDTO.setPhoneNumber(phoneNumber);
                customerDTO.setCusId(CusId);
                customerDTO.setName(name);

                boolean updateCustomer = cashierMakeReloadBO.updateCustomer(customerDTO);
                if (updateCustomer) {
                    Notification.notifie("Customer Data", "Customer Data Updated",
                            NotificationType.INFORMATION);
                    observableList.clear();
                    initialize();
                } else {
                    Notification.notifie("Customer Data", "Customer Data  Not Updated", NotificationType.ERROR);
                }
            }
        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }
    }

    public void placeOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        try{
            String reloadId = lblOrderId.getText();
            Date date = Date.valueOf(lblOrderDate.getText());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
            Time time = Time.valueOf(simpleDateFormat.format(new java.util.Date()));
            String cusId = tblCustomer.getSelectionModel().getSelectedItem().getCode();
            String employeeId=LoginFormController.employeeId;

            ArrayList<CusReloadDetailsDTO> cusReloadDetailsDTOArrayList =new ArrayList<>();
            for (int i = 0; i < tblOrder.getItems().size(); i++) {
                String reloadCode= String.valueOf(colReloadCode.getCellData(i));
                double total= Double.parseDouble(String.valueOf(colTotal.getCellData(i)));


                CusReloadDetailsDTO cusReloadDetailsDTO =new CusReloadDetailsDTO(reloadId,reloadCode,total);
                cusReloadDetailsDTOArrayList.add(cusReloadDetailsDTO);

            }
            CusReloadDTO cusReloadDTO =new CusReloadDTO(reloadId,date,time,cusId,employeeId
                    , cusReloadDetailsDTOArrayList);

            boolean placeReload= cashierMakeReloadBO.placeReload(cusReloadDTO);
            if (placeReload){
                Notification.notifie("Place Reload","Reload Added",NotificationType.INFORMATION);
                clearFields();
                initialize();
            }else{
                Notification.notifie("Place Reload","Reload Failed",NotificationType.ERROR);
            }

        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }

    }
    public void onMouseClicked(MouseEvent mouseEvent) {

        try{

            CustomerTm tm = tblCustomer.getItems().get(tblCustomer.getSelectionModel().getSelectedIndex());
            txtName.setText(tm.getName());
            txtPhoneNumber.setText(String.valueOf(tm.getPhoneNumber()));
            CusId = tm.getCode();
            lblCustomerName.setText(txtName.getText());
            btnAdd.setDisable(true);
            txtDescription.requestFocus();
        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }
    }


    public void onMouseClickTblOrder(MouseEvent mouseEvent) {

        try{
            ReloadTm reloadTm = tblOrder.getItems().get(tblOrder.getSelectionModel().getSelectedIndex());
            lblIReloadCode.setText(reloadTm.getReloadCode());
            txtDescription.setText(reloadTm.getServiceProvider());
            txtAmount.setText(String.valueOf(reloadTm.getTotalAmount()));

        }catch (Exception exception){
            Notification.notifie("Error",""+exception,NotificationType.ERROR);
        }

    }
}
