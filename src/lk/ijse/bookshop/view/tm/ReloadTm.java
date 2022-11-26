package lk.ijse.bookshop.view.tm;

public class ReloadTm {
    private String reloadCode;
    private String serviceProvider;
    private double totalAmount;

    public ReloadTm() {
        
    }

    public ReloadTm(String reloadCode, String serviceProvider, double totalAmount) {
        this.reloadCode = reloadCode;
        this.serviceProvider = serviceProvider;
        this.totalAmount = totalAmount;
    }

    public String getReloadCode() {
        return reloadCode;
    }

    public void setReloadCode(String reloadCode) {
        this.reloadCode = reloadCode;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
