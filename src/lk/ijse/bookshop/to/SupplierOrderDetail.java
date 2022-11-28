package lk.ijse.bookshop.to;

import java.sql.Date;
import java.sql.Time;

public class SupplierOrderDetail {
    private String SupOrderId;
    private String ItemId;
    private double unitPrice;
    private int quantity;
    private double totalPrice;

    public SupplierOrderDetail() {
    }

    public SupplierOrderDetail(String supOrderId, String itemId, double unitPrice, int quantity, double totalPrice) {
        SupOrderId = supOrderId;
        ItemId = itemId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getSupOrderId() {
        return SupOrderId;
    }

    public void setSupOrderId(String supOrderId) {
        SupOrderId = supOrderId;
    }

    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
