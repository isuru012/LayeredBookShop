package lk.ijse.bookshop.view.tm;

public class AdminItemTm {
    private String itemId;
    private int batchNumber;
    private String description;
    private double sellingUnitPrice;
    private double buyingUnitPrice;
    private int quantityOnHand;
    private double offerAmount;

    public AdminItemTm() {
    }

    public AdminItemTm(String itemId, int batchNumber, String description, double buyingUnitPrice, double sellingUnitPrice, int quantityOnHand, double offerAmount) {
        this.itemId = itemId;
        this.batchNumber = batchNumber;
        this.description = description;
        this.sellingUnitPrice = sellingUnitPrice;
        this.buyingUnitPrice = buyingUnitPrice;
        this.quantityOnHand = quantityOnHand;
        this.offerAmount = offerAmount;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSellingUnitPrice() {
        return sellingUnitPrice;
    }

    public void setSellingUnitPrice(double sellingUnitPrice) {
        this.sellingUnitPrice = sellingUnitPrice;
    }

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    public double getOfferAmount() {
        return offerAmount;
    }

    public void setOfferAmount(double offerAmount) {
        this.offerAmount = offerAmount;
    }

    public double getBuyingUnitPrice() {
        return buyingUnitPrice;
    }

    public void setBuyingUnitPrice(double buyingUnitPrice) {
        this.buyingUnitPrice = buyingUnitPrice;
    }
}
