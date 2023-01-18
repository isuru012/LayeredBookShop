package lk.ijse.bookshop.dto;

public class OrderDetailDTO {
    private String cusOrderId;
    private String itemId;
    private double unitPrice;
    private int quantity;
    private double totalPrice;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String cusOrderId, String itemId, double unitPrice, int quantity, double totalPrice) {
        this.cusOrderId = cusOrderId;
        this.itemId = itemId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getCusOrderId() {
        return cusOrderId;
    }

    public void setCusOrderId(String cusOrderId) {
        this.cusOrderId = cusOrderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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
