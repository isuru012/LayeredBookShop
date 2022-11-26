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
import lk.ijse.bookshop.model.CustomerModel;
import lk.ijse.bookshop.model.PlaceOrderModel;
import lk.ijse.bookshop.model.PlaceReloadModel;
import lk.ijse.bookshop.to.*;
import lk.ijse.bookshop.util.Notification;
import lk.ijse.bookshop.view.tm.CustomerTm;
import lk.ijse.bookshop.view.tm.OrderTm;
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

    public void initialize() throws SQLException, ClassNotFoundException {
        Platform.runLater(() -> txtSearch.requestFocus());
        btnPlaceOrder.setDisable(true);
        observableList.clear();
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colJoinedDate.setCellValueFactory(new PropertyValueFactory<>("joinedDate"));

        ArrayList arrayList = CustomerModel.getAllDetails();
        for (Object customerTm : arrayList) {
            observableList.add(customerTm);
        }

        searchPart();

        lblOrderDate.setText(String.valueOf(LocalDate.now()));
        lblOrderId.setText(generateNextReloadId(PlaceReloadModel.getOrderId()));
        ArrayList loadAllServiceProviders = PlaceReloadModel.loadAllServiceProviders();
        TextFields.bindAutoCompletion(txtDescription, loadAllServiceProviders);
    }

    private String generateNextReloadId(String orderId) {
        if (orderId != null) {
            String[] split = orderId.split("U0");
            int id = Integer.parseInt(split[1]);

            id += 1;
            return "U0" + id;
        }
        return "U01";
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
        if (!txtName.getText().equals("") && !txtPhoneNumber.getText().equals("")) {
            String name = txtName.getText();
            int phoneNumber = Integer.parseInt(txtPhoneNumber.getText());
            String cusId = generateNextCustomeId(CustomerModel.getOrderId());
            /*LocalDate date= LocalDate.now();*/
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            String employeeId = LoginFormController.employeeId;

            Customer customer = new Customer(cusId, name, phoneNumber, date, employeeId);
            boolean customerData = CustomerModel.insertCustomerData(customer);
            if (customerData) {
                Notification.notifie("Customer Data", "Customer Data Added", NotificationType.INFORMATION);
            } else {
                Notification.notifie("Customer Data", "Customer Data  Not Added", NotificationType.ERROR);
            }

            CustomerTm customerTm = new CustomerTm(cusId, name, phoneNumber, date);
            colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            colJoinedDate.setCellValueFactory(new PropertyValueFactory<>("joinedDate"));

            observableList.add(customerTm);
            searchPart();
        }
    }
    private String generateNextCustomeId(String orderId) {
        if (orderId != null) {
            String[] split = orderId.split("C0");
            int id = Integer.parseInt(split[1]);

            id += 1;
            return "C0" + id;
        }
        return "C01";
    }

    ObservableList<ReloadTm> observableList1 = FXCollections.observableArrayList();
    @FXML
    void addToCartOnAction(ActionEvent event) {
        addToTableOrder();
    }

    private void addToTableOrder() {
        String code = lblIReloadCode.getText();
        String description = txtDescription.getText();
        double amount = Integer.parseInt(txtAmount.getText());

        ReloadTm reloadTm=new ReloadTm(code,description,amount);


        observableList1.add(reloadTm);
        tblOrder.setItems(observableList1);
        generateTotal();
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
        addToTableOrder();
    }

    @FXML
    void cashOnReleased(KeyEvent event) {
        if (!txtCash.getText().equals("")) {
            double discount;
            btnPlaceOrder.setDisable(true);
            if (txtDiscount.getText().equals("")) {
                discount = 0;
            } else {

                discount = Double.parseDouble(txtDiscount.getText()) / 100 * Double.parseDouble(lblTotal.getText());
            }

            String balance = String.valueOf(Double.parseDouble(txtCash.getText()) - (Double.parseDouble(lblTotal.getText()) - discount));
            lblBalance.setText(balance);
            if (Double.parseDouble(lblBalance.getText()) >= 0) {
                btnPlaceOrder.setDisable(false);
            }
        }
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        tblOrder.getItems().removeAll(tblOrder.getSelectionModel().getSelectedItems());
        generateTotal();
    }

    @FXML
    void discountOnReleased(KeyEvent event) {
        if (!txtCash.getText().equals("")) {
            double discount;
            btnPlaceOrder.setDisable(true);
            if (txtDiscount.getText().equals("")) {
                discount = 0;
            } else {

                discount = Double.parseDouble(txtDiscount.getText()) / 100 * Double.parseDouble(lblTotal.getText());
            }

            String balance = String.valueOf(Double.parseDouble(txtCash.getText()) - (Double.parseDouble(lblTotal.getText()) - discount));
            lblBalance.setText(balance);
            if (Double.parseDouble(lblBalance.getText()) >= 0) {
                btnPlaceOrder.setDisable(false);
            }
        }
    }

    @FXML
    void keyReleasedOnAction(KeyEvent event) throws SQLException, ClassNotFoundException {
        String text = txtDescription.getText();
        Reload reload = PlaceReloadModel.searchDescription(text);
        if (reload != null) {
            lblIReloadCode.setText(reload.getReloadId());

            }

            if (event.getCode() == KeyCode.ENTER) {
                txtAmount.requestFocus();
            }
        }


    @FXML
    void onMouseClickRefresh(MouseEvent event) {
        clearFields();
        btnPlaceOrder.setDisable(false);
        btnAdd.setDisable(false);
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
        double amount = Integer.parseInt(txtAmount.getText());

        observableList1.get(tblOrder.getSelectionModel().getSelectedIndex()).setTotalAmount(amount);
        tblOrder.refresh();
        generateTotal();
    }

    @FXML
    void updateCustomerOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (tblCustomer.getSelectionModel().getSelectedIndex() != -1) {
            String name = txtName.getText();
            int phoneNumber = Integer.parseInt(txtPhoneNumber.getText());


            boolean updateCustomer = CustomerModel.updateCustomer(name, phoneNumber, CusId);
            if (updateCustomer) {
                Notification.notifie("Customer Data", "Customer Data Updated", NotificationType.INFORMATION);
                observableList.clear();
                initialize();
            } else {
                Notification.notifie("Customer Data", "Customer Data  Not Updated", NotificationType.ERROR);
            }
        }
    }

    public void placeOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String reloadId = lblOrderId.getText();
        Date date = Date.valueOf(lblOrderDate.getText());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        Time time = Time.valueOf(simpleDateFormat.format(new java.util.Date()));
        String cusId = tblCustomer.getSelectionModel().getSelectedItem().getCode();
        String employeeId=LoginFormController.employeeId;
        ArrayList<CustomerReloadDetail> customerReloadDetailArrayList=new ArrayList<>();
        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            String reloadCode= String.valueOf(colReloadCode.getCellData(i));
            double total= Double.parseDouble(String.valueOf(colTotal.getCellData(i)));


            CustomerReloadDetail customerReloadDetail=new CustomerReloadDetail(reloadId,reloadCode,total);
            customerReloadDetailArrayList.add(customerReloadDetail);

        }
        CustomerReload customerReload=new CustomerReload(reloadId,date,time,cusId,employeeId,customerReloadDetailArrayList);

        boolean placeReload= PlaceReloadModel.placeReload(customerReload);
        if (placeReload){
            Notification.notifie("Place Order","Order Added",NotificationType.INFORMATION);
            clearFields();
            initialize();
        }else{
            Notification.notifie("Place Order","Order Failed",NotificationType.ERROR);
        }

    }

    public void onMouseClickTblOrder(MouseEvent mouseEvent) {
        ReloadTm reloadTm = tblOrder.getItems().get(tblOrder.getSelectionModel().getSelectedIndex());
        lblIReloadCode.setText(reloadTm.getReloadCode());
        txtDescription.setText(reloadTm.getServiceProvider());
        txtAmount.setText(String.valueOf(reloadTm.getTotalAmount()));

    }
}
