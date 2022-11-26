package lk.ijse.bookshop.to;

public class CustomerReloadDetail {
    private String cusReloadId;
    private String reloadId;
    private double totalPrice;

    public CustomerReloadDetail() {
    }

    public CustomerReloadDetail(String cusReloadId, String reloadId, double totalPrice) {
        this.cusReloadId = cusReloadId;
        this.reloadId = reloadId;
        this.totalPrice = totalPrice;
    }

    public String getCusReloadId() {
        return cusReloadId;
    }

    public void setCusReloadId(String cusReloadId) {
        this.cusReloadId = cusReloadId;
    }

    public String getReloadId() {
        return reloadId;
    }

    public void setReloadId(String reloadId) {
        this.reloadId = reloadId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
