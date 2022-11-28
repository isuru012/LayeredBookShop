package lk.ijse.bookshop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bookshop.model.PlaceOrderModel;
import lk.ijse.bookshop.model.SupplierOrderModel;
import lk.ijse.bookshop.to.*;
import lk.ijse.bookshop.util.Navigation;
import lk.ijse.bookshop.util.Notification;
import lk.ijse.bookshop.util.Routes;
import lk.ijse.bookshop.view.tm.SupplierTm;
import org.controlsfx.control.textfield.TextFields;
import tray.notification.NotificationType;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class AdminSupplierController {

    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    @FXML
    private AnchorPane pane2;

    @FXML
    private TableView<SupplierTm> tblSupplierOrder;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colQuantity;


    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblSupplierId;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblItemCode;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private Label lblTotalPrice;

    @FXML
    private JFXButton btnNew;

    @FXML
    private JFXButton btnSeeSupplierDetails;

    @FXML
    public  JFXTextField txtSupplierName;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtBuyingUnitPrice;

    @FXML
    private JFXTextField txtSellingUnitPrice;

    public static double sellingUnitPrice=0;
    ObservableList<SupplierTm> observableList1 = FXCollections.observableArrayList();

    public void initialize() throws SQLException, ClassNotFoundException {
        Platform.runLater(() -> txtSupplierName.requestFocus());
        lblOrderDate.setText(String.valueOf(LocalDate.now()));
        lblOrderId.setText(generateNextOrderId(SupplierOrderModel.getOrderId()));
        ArrayList loadAllSupplierNames = SupplierOrderModel.loadAllSupplierNames();
        TextFields.bindAutoCompletion(txtSupplierName, loadAllSupplierNames);

        ArrayList loadAllDescriptionIds = SupplierOrderModel.loadAllDescriptionIds();
        TextFields.bindAutoCompletion(txtDescription, loadAllDescriptionIds);

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        if (tblSupplierOrder.getItems().size() >= 1) {
            btnPlaceOrder.setDisable(false);
        } else {
            btnPlaceOrder.setDisable(true);
        }



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


    @FXML
    void addOnAction(ActionEvent event) {
        addToTable();
    }

    private void generateTotal() {
        double total = 0;
        for (int i = 0; i < tblSupplierOrder.getItems().size(); i++) {
            total += Double.parseDouble(String.valueOf(colTotal.getCellData(i)));

        }
        lblTotalPrice.setText(String.valueOf(total));

    }

    private void clearFields() {
        txtSupplierName.setText("");

        lblItemCode.setText("");
        lblSupplierId.setText("");
        txtQty.setText("");
        txtDescription.setText("");

        lblQtyOnHand.setText("");
        lblTotalPrice.setText("");
        txtSellingUnitPrice.setText("0");
        lblQtyOnHand.setText("");
        txtBuyingUnitPrice.setText("");
        tblSupplierOrder.getItems().clear();
    }

    private void checkPlaceOrder() {
        if (tblSupplierOrder.getItems().size() >= 1) {
            btnPlaceOrder.setDisable(false);
        } else {
            btnPlaceOrder.setDisable(true);
        }
    }

    @FXML
    void btnNewSupplierOrderOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        clearFields();
        btnAdd.setDisable(false);
        btnPlaceOrder.setDisable(false);
        checkPlaceOrder();
    }

    @FXML
    void btnSeeSupplierDetailsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.SUPPLIERDETAILS, pane2);
    }

    @FXML
    void deleteOnAction(ActionEvent event) {
        tblSupplierOrder.getItems().removeAll(tblSupplierOrder.getSelectionModel().getSelectedItems());
        generateTotal();
        checkPlaceOrder();
        clearTableSelection();
        btnAdd.setDisable(false);

    }

    public void clearTableSelection() {
        if (!tblSupplierOrder.isFocused()) {
            tblSupplierOrder.getSelectionModel().clearSelection();
        }
    }

    @FXML
    void onMouseClickedSupplierOrder(MouseEvent event) throws SQLException, ClassNotFoundException {
        SupplierTm supplierTm = tblSupplierOrder.getItems().get(tblSupplierOrder.getSelectionModel().getSelectedIndex());
        lblItemCode.setText(supplierTm.getItemCode());
        txtDescription.setText(supplierTm.getDescription());
        txtQty.setText(String.valueOf(supplierTm.getQuantity()));
        lblQtyOnHand.setText(String.valueOf(SupplierOrderModel.getItemQuantity(lblItemCode.getText())));
        txtBuyingUnitPrice.setText(String.valueOf(SupplierOrderModel.getSellingUnitPrice(lblItemCode.getText())));
        btnAdd.setDisable(true);
        checkPlaceOrder();


    }

    @FXML
    void placeOrderOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String SupOrderId = lblOrderId.getText();
        Date date = Date.valueOf(lblOrderDate.getText());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        Time time = Time.valueOf(simpleDateFormat.format(new java.util.Date()));
        String SupplierId = lblSupplierId.getText();
        String getUsername=SupplierOrderModel.getUserName();

        sellingUnitPrice= Double.parseDouble(txtSellingUnitPrice.getText());


        ArrayList<SupplierOrderDetail> supplierOrderDetails = new ArrayList<>();
        for (int i = 0; i < tblSupplierOrder.getItems().size(); i++) {
            String itemCode = String.valueOf(colItemCode.getCellData(i));
            double unitPrice = Double.parseDouble(String.valueOf(colUnitPrice.getCellData(i)));
            int orderQuantity = Integer.parseInt(String.valueOf(colQuantity.getCellData(i)));
            double total = unitPrice * orderQuantity;

            SupplierOrderDetail supplierOrderDetail = new SupplierOrderDetail(SupOrderId,itemCode,unitPrice,orderQuantity
                    ,total);
            supplierOrderDetails.add(supplierOrderDetail);

        }
        SupplierOrder supplierOrder = new SupplierOrder(SupOrderId,date,time,SupplierId,getUsername,supplierOrderDetails);
        boolean placeOrder = SupplierOrderModel.placeOrder(supplierOrder);
        if (placeOrder) {
            Notification.notifie("Place Order", "Order Added", NotificationType.INFORMATION);
            clearFields();
            initialize();
        } else {
            Notification.notifie("Place Order", "Order Failed", NotificationType.ERROR);
        }

    }

    @FXML
    void updateOnAction(ActionEvent event) {
        int qty = Integer.parseInt(txtQty.getText());
        double buyingUnitPrice = Double.parseDouble(txtBuyingUnitPrice.getText());

        observableList1.get(tblSupplierOrder.getSelectionModel().getSelectedIndex()).setQuantity(qty);
        observableList1.get(tblSupplierOrder.getSelectionModel().getSelectedIndex()).setTotal(qty * buyingUnitPrice);
        tblSupplierOrder.refresh();
        generateTotal();
        checkPlaceOrder();
        clearTableSelection();
        btnAdd.setDisable(false);
    }

    public void keyReleasedOnActionSupplierName(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        String text = txtSupplierName.getText();
        Supplier supplier = SupplierOrderModel.searchName(text);
        if (supplier != null) {
            lblSupplierId.setText(supplier.getSupplierId());

            if (keyEvent.getCode() == KeyCode.ENTER) {
                txtDescription.requestFocus();
            }
        }
        clearTableSelection();
        btnAdd.setDisable(false);
    }

    public void keyReleasedOnActionDescription(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        String text = txtDescription.getText();
        Item item = SupplierOrderModel.searchDescription(text);
        if (item != null) {
            lblItemCode.setText(item.getItemId());

            lblQtyOnHand.setText(String.valueOf(SupplierOrderModel.getQtyTotalOfOneItem(item.getItemId())));

            if (keyEvent.getCode() == KeyCode.ENTER) {
                txtBuyingUnitPrice.requestFocus();
            }
        }
        clearTableSelection();
        btnAdd.setDisable(false);
    }

    public void keyReleasedOnActionQuantity(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            addToTable();
            checkPlaceOrder();

        }


    }

    private void addToTable() {
        if (!btnAdd.isDisabled()) {
            String code = lblItemCode.getText();
            String description = txtDescription.getText();
            int qty = Integer.parseInt(txtQty.getText());
            double buyingUnitPrice = Double.parseDouble(txtBuyingUnitPrice.getText());
            double total = qty * buyingUnitPrice;

            SupplierTm supplierTm = new SupplierTm(code, description, qty, buyingUnitPrice, total);


            for (int i = 0; i < tblSupplierOrder.getItems().size(); i++) {
                if (colItemCode.getCellData(i).equals(lblItemCode.getText())) {

                    int tempQty = observableList1.get(i).getQuantity() + qty;
                    double tempTotal = buyingUnitPrice * tempQty;

                    observableList1.get(i).setQuantity(tempQty);
                    observableList1.get(i).setTotal(tempTotal);

                    tblSupplierOrder.refresh();
                    generateTotal();
                    checkPlaceOrder();
                    return;

                }
            }
            observableList1.add(supplierTm);
            tblSupplierOrder.setItems(observableList1);
            generateTotal();
            checkPlaceOrder();
        }
    }

    public void keyReleasedOnActionBuyingUnitPrice(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            txtSellingUnitPrice.requestFocus();
        }
    }

    public void keyReleasedOnActionSellingUnitPrice(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            txtQty.requestFocus();
        }
    }
}
