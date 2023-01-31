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
import lk.ijse.bookshop.bo.BOFactory;
import lk.ijse.bookshop.bo.custom.CashierPlaceOrderBO;
import lk.ijse.bookshop.db.DBConnection;
import lk.ijse.bookshop.dto.CustomerDTO;
import lk.ijse.bookshop.dto.CusOrderDTO;
import lk.ijse.bookshop.dto.OrderDetailDTO;
import lk.ijse.bookshop.dto.ItemDTO;
import lk.ijse.bookshop.util.Notification;
import lk.ijse.bookshop.view.tm.CustomerTm;
import lk.ijse.bookshop.view.tm.OrderTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import org.controlsfx.control.textfield.TextFields;
import tray.notification.NotificationType;

import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.RED;

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
    static boolean boolPassword = false;

    CashierPlaceOrderBO cashierPlaceOrderBO= (CashierPlaceOrderBO) BOFactory.getBOFactory().
            getBOTypes(BOFactory.BOTypes.CASHIERPLACEORDER);

    public void initialize() throws SQLException, ClassNotFoundException {
        Platform.runLater(() -> txtSearch.requestFocus());
        btnPlaceOrder.setDisable(true);
        observableList.clear();
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colJoinedDate.setCellValueFactory(new PropertyValueFactory<>("joinedDate"));

        ArrayList arrayList = cashierPlaceOrderBO.getAllDetails();
        for (Object customerTm : arrayList) {
            observableList.add(customerTm);
        }
        /*tblCustomer.setItems(observableList);*/

        searchPart();

        lblOrderDate.setText(String.valueOf(LocalDate.now()));
        lblOrderId.setText(generateNextOrderId(cashierPlaceOrderBO.getOrderId()));
        ArrayList loadAllDescriptionIds = cashierPlaceOrderBO.loadAllDescriptionIds();
        TextFields.bindAutoCompletion(txtDescription, loadAllDescriptionIds);

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("sellingUnitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

    }

    private String generateNextOrderId(String orderId) {
        if (orderId != null) {
            String[] split = orderId.split("O");
            int id = Integer.parseInt(split[1]);

            id += 1;
            if (id >= 10) {
                return "O000" + id;
            } else if (id >= 100) {
                return "O00" + id;
            } else if (id >= 1000) {
                return "O0" + id;
            } else if (id >= 10000) {
                return "O" + id;
            } else {

                return "O0000" + id;
            }
        }
        return "O00001";
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
            String[] split = orderId.split("C");
            int id = Integer.parseInt(split[1]);

            id += 1;
            if (id >= 10) {
                return "C000" + id;
            } else if (id >= 100) {
                return "C00" + id;
            } else if (id >= 1000) {
                return "C0" + id;
            } else if (id >= 10000) {
                return "C" + id;
            } else {

                return "C0000" + id;
            }
        }
        return "C00001";
    }


    @FXML
    void deleteOnAction(ActionEvent event) {
        tblOrder.getItems().removeAll(tblOrder.getSelectionModel().getSelectedItems());
        generateTotal();

    }


    public void onMouseClicked(MouseEvent mouseEvent) {

        try {

            CustomerTm tm = tblCustomer.getItems().get(tblCustomer.getSelectionModel().getSelectedIndex());
            txtName.setText(tm.getName());
            txtPhoneNumber.setText(String.valueOf(tm.getPhoneNumber()));
            CusId = tm.getCode();
            lblCustomerName.setText(txtName.getText());
            btnAdd.setDisable(true);
            txtDescription.requestFocus();
        } catch (Exception exception) {
            Notification.notifie("Error", "" + exception, NotificationType.ERROR);
        }
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

            CustomerDTO customerDTO=new CustomerDTO();
            customerDTO.setName(name);
            customerDTO.setPhoneNumber(phoneNumber);
            customerDTO.setCusId(CusId);


            boolean updateCustomer = cashierPlaceOrderBO.updateCustomer(customerDTO);
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
            String cusId = generateNextCustomeId(cashierPlaceOrderBO.getCusId());
            /*LocalDate date= LocalDate.now();*/
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            String employeeId = LoginFormController.employeeId;

            CustomerDTO customerDTO = new CustomerDTO(cusId, name, phoneNumber, date, employeeId);
            boolean customerData = cashierPlaceOrderBO.insertCustomerData(customerDTO);
            if (customerData) {
                Notification.notifie("Customer Data", "Customer Data Added", NotificationType.INFORMATION);
            } else {
                Notification.notifie("Customer Data", "Customer Data  Not Added", NotificationType.ERROR);
            }

            CustomerTm customerTm = new CustomerTm(cusId, name, phoneNumber, date);

            observableList.add(customerTm);
            searchPart();
        }


    }

    ObservableList<OrderTm> observableList1 = FXCollections.observableArrayList();

    public void addToCartOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Pattern pattern2 = Pattern.compile("^[0-9]*$");
        Matcher matcher2 = pattern2.matcher(txtQty.getText());

        boolPassword = matcher2.matches();
        if (boolPassword) {
            addToTableOrder();
        }


    }


    public void placeOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String orderId = lblOrderId.getText();
        Date date = Date.valueOf(lblOrderDate.getText());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        Time time = Time.valueOf(simpleDateFormat.format(new java.util.Date()));
        String cusId = tblCustomer.getSelectionModel().getSelectedItem().getCode();

        String employeeId = LoginFormController.employeeId;

        ArrayList<OrderDetailDTO> orderDetailDTOArrayList = new ArrayList<>();

        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            String itemCode = String.valueOf(colItemCode.getCellData(i));
            double unitPrice = Double.parseDouble(String.valueOf(colUnitPrice.getCellData(i)));
            int orderQuantity = Integer.parseInt(String.valueOf(colQty.getCellData(i)));
            double total = unitPrice * orderQuantity;

            OrderDetailDTO orderDetailDTO = new OrderDetailDTO(orderId, itemCode, unitPrice, orderQuantity, total);
            orderDetailDTOArrayList.add(orderDetailDTO);

        }
        CusOrderDTO cusOrderDTO = new CusOrderDTO(orderId, date, time, cusId, employeeId, orderDetailDTOArrayList);

        boolean placeOrder = cashierPlaceOrderBO.placeOrder(cusOrderDTO);
        if (placeOrder) {
            InputStream resource = this.getClass().getResourceAsStream("../report/bk3.jrxml");


            HashMap<String, Object> hm2 = new HashMap<>();

            hm2.put("total", Double.parseDouble(lblTotal.getText()));
            hm2.put("cash", Double.parseDouble(txtCash.getText()));
            hm2.put("balance", Double.parseDouble(lblBalance.getText()));
            hm2.put("orderId", lblOrderId.getText());

            try {
                JasperReport jasperReport = JasperCompileManager.compileReport(resource);

                JasperPrint jasperPrint = JasperFillManager.
                        fillReport(jasperReport, hm2, DBConnection.getDBConnection().getConnection());

                JasperViewer.viewReport(jasperPrint, false);
            } catch (Exception e) {
                Notification.notifie("ERROR", "" + e, NotificationType.ERROR);
            }


            Notification.notifie("Place Order", "Order Added", NotificationType.INFORMATION);
            clearFields();
            initialize();
        } else {
            Notification.notifie("Place Order", "Order Failed", NotificationType.ERROR);
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
        tblOrder.getItems().clear();
    }

    static String itemId = null;
    static ObservableList observableListKey = FXCollections.observableArrayList();

    public void keyReleasedOnAction(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            observableListKey.clear();
            String text = txtDescription.getText();
            ItemDTO item = cashierPlaceOrderBO.searchDescription(text);
            if (item != null) {
                lblItemCode.setText(item.getItemId());
                ArrayList allItemPrices = cashierPlaceOrderBO.getAllItemPrices(lblItemCode.getText());

                for (Object unitPrice : allItemPrices) {
                    observableListKey.add(Double.parseDouble(String.valueOf(unitPrice)));
                }


                cmbUnitPrice.setItems(observableListKey);
                cmbUnitPrice.getSelectionModel().selectFirst();
                double unitPrice = Double.parseDouble(String.valueOf(cmbUnitPrice.getSelectionModel().getSelectedItem()));

                itemId = item.getItemId();
                lblQtyOnHand.setText(String.valueOf(cashierPlaceOrderBO.getQtyTotalOfOneItem(item.getItemId(),unitPrice)));


                /*cmbUnitPrice.getSelectionModel().selectFirst();*/
                txtQty.requestFocus();
            }
        }

    }

    public void onMouseClickRefresh(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {

        try {

            clearFields();

            btnPlaceOrder.setDisable(false);
            btnAdd.setDisable(false);
        } catch (Exception exception) {
            Notification.notifie("Error", "" + exception, NotificationType.ERROR);
        }

    }

    public void onMouseClickOrder(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {

        try {

            OrderTm orderTm = tblOrder.getItems().get(tblOrder.getSelectionModel().getSelectedIndex());
            lblItemCode.setText(orderTm.getCode());
            txtDescription.setText(orderTm.getDescription());
            txtQty.setText(String.valueOf(orderTm.getQty()));
            lblQtyOnHand.setText(String.valueOf(cashierPlaceOrderBO.getItemQuantity(lblItemCode.getText())));
            ArrayList allItemPrices = cashierPlaceOrderBO.getAllItemPrices(lblItemCode.getText());
            ObservableList observableList = FXCollections.observableArrayList();
            for (Object unitPrice : allItemPrices) {
                observableList.add(unitPrice);
            }

            cmbUnitPrice.getSelectionModel().selectFirst();
            cmbUnitPrice.setItems(observableList);
        } catch (Exception exception) {
            Notification.notifie("Error", "" + exception, NotificationType.ERROR);
        }


    }

    public void addToCartOnEnterKey(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {


        Pattern pattern2 = Pattern.compile("^[0-9]*$");
        Matcher matcher2 = pattern2.matcher(txtQty.getText());

        if (matcher2.matches()) {
            txtQty.setFocusColor(BLUE);
            if (keyEvent.getCode() == KeyCode.ENTER) {

                addToTableOrder();

            }
        } else {
            txtQty.setFocusColor(RED);
        }


    }

    private void addToTableOrder() throws SQLException, ClassNotFoundException {

        String code = lblItemCode.getText();
        String description = txtDescription.getText();
        double sellingUnitPrice = Double.parseDouble(String.valueOf(cmbUnitPrice.getSelectionModel().getSelectedItem()));

        int qty = Integer.parseInt(txtQty.getText());
        double itemDiscount;
        if (txtItemDiscount.getText().equals("")){
            itemDiscount=0;
        }else{
            itemDiscount= Double.parseDouble(txtItemDiscount.getText());
        }
        double offerAmount=cashierPlaceOrderBO.getOfferAmount(code,sellingUnitPrice);

        sellingUnitPrice=sellingUnitPrice-(itemDiscount+offerAmount);
        double total = qty * sellingUnitPrice;

        OrderTm orderTm = new OrderTm(code, description, qty, sellingUnitPrice, total);


        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            if (colItemCode.getCellData(i).equals(lblItemCode.getText()) && colUnitPrice.getCellData(i)
                    .equals(/*cmbUnitPrice.getSelectionModel().getSelectedItem())*/sellingUnitPrice)) {

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

            String balance = String.valueOf(Double.parseDouble(txtCash.getText()) -
                    (Double.parseDouble(lblTotal.getText()) - discount));
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

            String balance = String.valueOf(Double.parseDouble(txtCash.getText()) - (Double.parseDouble(lblTotal.getText())
                    - discount));
            lblBalance.setText(balance);
            if (Double.parseDouble(lblBalance.getText()) >= 0) {
                btnPlaceOrder.setDisable(false);
            }
        }

    }

    public void onCmbUnitPrice(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (cmbUnitPrice.getSelectionModel().getSelectedItem() != null) {
            double unitPrice = Double.parseDouble(String.valueOf(cmbUnitPrice.getSelectionModel().getSelectedItem()));
            lblQtyOnHand.setText(String.valueOf(cashierPlaceOrderBO.getQtyTotalOfOneItem(itemId, unitPrice)));
        }


    }
}
