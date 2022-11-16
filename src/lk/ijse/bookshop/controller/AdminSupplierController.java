package lk.ijse.bookshop.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bookshop.util.Navigation;
import lk.ijse.bookshop.util.Routes;

import java.io.IOException;

public class AdminSupplierController {

    public AnchorPane pane2;
    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblOrderDate;

    @FXML
    private JFXComboBox<?> cmbSupplierId;

    @FXML
    private Label lblSupplierName;

    @FXML
    private JFXComboBox<?> cmbItemCode;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private TextField txtQty;

    @FXML
    private Label lblTotalPrice;

    @FXML
    void addOnAction(ActionEvent event) {

    }

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnSeeSupplierDetailsOnAction(ActionEvent event) throws IOException {
        Navigation.navigate(Routes.SUPPLIERDETAILS,pane2);
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) {

    }

    @FXML
    void cmbSupIdOnAction(ActionEvent event) {

    }

    @FXML
    void deleteOnAction(ActionEvent event) {

    }

    @FXML
    void placeOrderOnAction(ActionEvent event) {

    }

}
