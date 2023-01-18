package lk.ijse.bookshop.entity;

public class CusReloadDetails {
    private String cusReloadId;
    private String reloadId;
    private double totalPrice;

    public CusReloadDetails() {
    }

    public CusReloadDetails(String cusReloadId, String reloadId, double totalPrice) {
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

    @Override
    public String toString() {
        return "CusReloadDetails{" +
                "cusReloadId='" + cusReloadId + '\'' +
                ", reloadId='" + reloadId + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
