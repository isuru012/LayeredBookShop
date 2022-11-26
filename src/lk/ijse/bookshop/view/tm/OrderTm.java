package lk.ijse.bookshop.view.tm;

public class OrderTm {
private String code;
private String description;
private int qty;
private double sellingUnitPrice;
private double total;

    public OrderTm() {

    }

    public OrderTm(String code, String description, int qty, double sellingUnitPrice, double total) {
        this.code = code;
        this.description = description;
        this.qty = qty;
        this.sellingUnitPrice = sellingUnitPrice;
        this.total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getSellingUnitPrice() {
        return sellingUnitPrice;
    }

    public void setSellingUnitPrice(double sellingUnitPrice) {
        this.sellingUnitPrice = sellingUnitPrice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
