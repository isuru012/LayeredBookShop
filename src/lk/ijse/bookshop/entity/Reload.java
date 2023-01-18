package lk.ijse.bookshop.entity;

public class Reload {
    private String reloadId;
    private String serviceProvider;
    private double reloadAmount;
    private double profitPercentage;

    public Reload() {
    }

    public Reload(String reloadId, String serviceProvider, double reloadAmount, double profitPercentage) {
        this.reloadId = reloadId;
        this.serviceProvider = serviceProvider;
        this.reloadAmount = reloadAmount;
        this.profitPercentage = profitPercentage;
    }

    public String getReloadId() {
        return reloadId;
    }

    public void setReloadId(String reloadId) {
        this.reloadId = reloadId;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public double getReloadAmount() {
        return reloadAmount;
    }

    public void setReloadAmount(double reloadAmount) {
        this.reloadAmount = reloadAmount;
    }

    public double getProfitPercentage() {
        return profitPercentage;
    }

    public void setProfitPercentage(double profitPercentage) {
        this.profitPercentage = profitPercentage;
    }

    @Override
    public String toString() {
        return "Reload{" +
                "reloadId='" + reloadId + '\'' +
                ", serviceProvider='" + serviceProvider + '\'' +
                ", reloadAmount=" + reloadAmount +
                ", profitPercentage=" + profitPercentage +
                '}';
    }
}
