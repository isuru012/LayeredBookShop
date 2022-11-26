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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.bookshop.model.CustomerModel;
import lk.ijse.bookshop.model.PlaceOrderModel;
import lk.ijse.bookshop.to.Customer;
import lk.ijse.bookshop.to.CustomerOrder;
import lk.ijse.bookshop.to.CustomerOrderDetail;
import lk.ijse.bookshop.to.Item;
import lk.ijse.bookshop.util.Notification;
import lk.ijse.bookshop.view.tm.CustomerTm;
import lk.ijse.bookshop.view.tm.OrderTm;
import org.controlsfx.control.textfield.TextFields;
import tray.notification.NotificationType;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class CashierPlaceOrderController {

    public JFXComboBox cmbBatchNumber;

    public ComboBox jComboBox;
    public JFXTextField txtDescription;
    public Label lblItemCode;
    public JFXButton btnAdd;
    public JFXComboBox cmbUnitPrice;
    public Label lblTotal;
    public JFXTextField txtDiscount;
    public JFXTextField txtCash;
    public JFXTextField txtItemDiscount;
    public JFXTextField txtQty;
    public Label lblBalance;
    public JFXButton btnPlaceOrder;
    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private TableView<CustomerTm> tblCustomer;

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
    private TableView<OrderTm> tblOrder;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn colQty;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn colTotal;


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
        /*tblCustomer.setItems(observableList);*/

        searchPart();

        lblOrderDate.setText(String.valueOf(LocalDate.now()));
        lblOrderId.setText(generateNextOrderId(PlaceOrderModel.getOrderId()));
        ArrayList loadAllDescriptionIds = PlaceOrderModel.loadAllDescriptionIds();
        TextFields.bindAutoCompletion(txtDescription, loadAllDescriptionIds);

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("sellingUnitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

    }

    private String generateNextOrderId(String orderId) {
        if (orderId != null) {
            String[] split = orderId.split("O0");
            int id = Integer.parseInt(split[1]);

            id += 1;
            return "O0" + id;
        }
        return "O01";
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

    private String generateNextCustomeId(String orderId) {
        if (orderId != null) {
            String[] split = orderId.split("C0");
            int id = Integer.parseInt(split[1]);

            id += 1;
            return "C0" + id;
        }
        return "C01";
    }


    @FXML
    void deleteOnAction(ActionEvent event) {
        tblOrder.getItems().removeAll(tblOrder.getSelectionModel().getSelectedItems());
        generateTotal();

    }


    public void onMouseClicked(MouseEvent mouseEvent) {
        CustomerTm tm = tblCustomer.getItems().get(tblCustomer.getSelectionModel().getSelectedIndex());
        txtName.setText(tm.getName());
        txtPhoneNumber.setText(String.valueOf(tm.getPhoneNumber()));
        CusId = tm.getCode();
        lblCustomerName.setText(txtName.getText());
        btnAdd.setDisable(true);
        txtDescription.requestFocus();
    }

    public void updateCartOnAction(ActionEvent actionEvent) {
        int qty = Integer.parseInt(txtQty.getText());
        double sellingUnitPrice = Double.parseDouble(String.valueOf(cmbUnitPrice.getSelectionModel().getSelectedItem()));

        observableList1.get(tblOrder.getSelectionModel().getSelectedIndex()).setQty(qty);
        observableList1.get(tblOrder.getSelectionModel().getSelectedIndex()).setTotal(qty * sellingUnitPrice);
        tblOrder.refresh();
        generateTotal();

    }

    public void updateCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
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

    public void addCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
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

    ObservableList<OrderTm> observableList1 = FXCollections.observableArrayList();

    public void addToCartOnAction(ActionEvent actionEvent) {

        addToTableOrder();


    }


    public void placeOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String orderId = lblOrderId.getText();
        Date date = Date.valueOf(lblOrderDate.getText());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        Time time = Time.valueOf(simpleDateFormat.format(new java.util.Date()));
        String cusId = tblCustomer.getSelectionModel().getSelectedItem().getCode();
        String employeeId=LoginFormController.employeeId;
        ArrayList<CustomerOrderDetail> customerOrderDetailArrayList=new ArrayList<>();
        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            String itemCode= String.valueOf(colItemCode.getCellData(i));
            double unitPrice=Double.parseDouble(String.valueOf(colUnitPrice.getCellData(i)));
            int orderQuantity=Integer.parseInt(String.valueOf(colQty.getCellData(i)));
            double total=unitPrice*orderQuantity;

            CustomerOrderDetail customerOrderDetail=new CustomerOrderDetail(orderId,itemCode,unitPrice,orderQuantity,total);
            customerOrderDetailArrayList.add(customerOrderDetail);

        }
        CustomerOrder customerOrder=new CustomerOrder(orderId,date,time,cusId,employeeId,customerOrderDetailArrayList);
           boolean placeOrder= PlaceOrderModel.placeOrder(customerOrder);
           if (placeOrder){
               Notification.notifie("Place Order","Order Added",NotificationType.INFORMATION);
               clearFields();
               initialize();
           }else{
               Notification.notifie("Place Order","Order Failed",NotificationType.ERROR);
           }


    }

    private void clearFields() {
        txtName.setText("");
        txtPhoneNumber.setText("");
        txtSearch.setText("");
        txtCash.setText("");
        txtQty.setText("");
        txtDescription.setText("");
        txtDiscount.setText("");
        txtItemDiscount.setText("");
        lblTotal.setText("");
        lblBalance.setText("");
        lblQtyOnHand.setText("");
        lblItemCode.setText("");
        lblCustomerName.setText("");
        cmbUnitPrice.getItems().clear();
        tblOrder.getItems().clear();
    }

    public void keyReleasedOnAction(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        String text = txtDescription.getText();
        Item item = PlaceOrderModel.searchDescription(text);
        if (item != null) {
            lblItemCode.setText(item.getItemId());
            ArrayList allItemPrices = PlaceOrderModel.getAllItemPrices(lblItemCode.getText());
            ObservableList observableList = FXCollections.observableArrayList();
            for (Object unitPrice : allItemPrices) {
                observableList.add(unitPrice);
            }

            cmbUnitPrice.getSelectionModel().selectFirst();
            cmbUnitPrice.setItems(observableList);
            lblQtyOnHand.setText(String.valueOf(item.getQuantityOnHand()));

            if (keyEvent.getCode() == KeyCode.ENTER) {
                txtQty.requestFocus();
            }
        }
    }

    public void onMouseClickRefresh(MouseEvent mouseEvent) {
        clearFields();
        btnPlaceOrder.setDisable(false);
        btnAdd.setDisable(false);

    }

    public void onMouseClickOrder(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        OrderTm orderTm = tblOrder.getItems().get(tblOrder.getSelectionModel().getSelectedIndex());
        lblItemCode.setText(orderTm.getCode());
        txtDescription.setText(orderTm.getDescription());
        txtQty.setText(String.valueOf(orderTm.getQty()));
        lblQtyOnHand.setText(String.valueOf(PlaceOrderModel.getItemQuantity(lblItemCode.getText())));
        ArrayList allItemPrices = PlaceOrderModel.getAllItemPrices(lblItemCode.getText());
        ObservableList observableList = FXCollections.observableArrayList();
        for (Object unitPrice : allItemPrices) {
            observableList.add(unitPrice);
        }

        cmbUnitPrice.getSelectionModel().selectFirst();
        cmbUnitPrice.setItems(observableList);


    }

    public void addToCartOnEnterKey(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            addToTableOrder();

        }


    }

    private void addToTableOrder() {
        String code = lblItemCode.getText();
        String description = txtDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double sellingUnitPrice = Double.parseDouble(String.valueOf(cmbUnitPrice.getSelectionModel().getSelectedItem()));
        double total = qty * sellingUnitPrice;

        OrderTm orderTm = new OrderTm(code, description, qty, sellingUnitPrice, total);


        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            if (colItemCode.getCellData(i).equals(lblItemCode.getText())) {

                int tempQty = observableList1.get(i).getQty() + qty;
                double tempTotal = sellingUnitPrice * tempQty;

                observableList1.get(i).setQty(tempQty);
                observableList1.get(i).setTotal(tempTotal);

                tblOrder.refresh();
                generateTotal();
                return;
            }
        }
        observableList1.add(orderTm);
        tblOrder.setItems(observableList1);
        generateTotal();
    }

    public void generateTotal() {
        double total = 0;
        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            total += Double.parseDouble(String.valueOf(colTotal.getCellData(i)));

        }
        lblTotal.setText(String.valueOf(total));

    }

    public void cashOnReleased(KeyEvent keyEvent) {
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

    public void discountOnReleased(KeyEvent keyEvent) {
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
}
