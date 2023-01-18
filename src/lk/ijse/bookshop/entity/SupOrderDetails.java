package lk.ijse.bookshop.entity;

public class SupOrderDetails {
    private String supOrderId;
    private String itemId;
    private double unitPrice;
    private int quantity;
    private double totalPrice;

    public SupOrderDetails() {
    }

    public SupOrderDetails(String supOrderId, String itemId, double unitPrice, int quantity, double totalPrice) {
        this.supOrderId = supOrderId;
        this.itemId = itemId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public String getSupOrderId() {
        return supOrderId;
    }

    public void setSupOrderId(String supOrderId) {
        this.supOrderId = supOrderId;
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

    @Override
    public String toString() {
        return "SupOrderDetails{" +
                "supOrderId='" + supOrderId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
