package lk.ijse.bookshop.dto;

public class CusReloadDetailsDTO {
    private String cusReloadId;
    private String reloadId;
    private double totalPrice;

    public CusReloadDetailsDTO() {
    }

    public CusReloadDetailsDTO(String cusReloadId, String reloadId, double totalPrice) {
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
