package lk.ijse.bookshop.view.tm;

public class ItemTm {
    private String itemId;
    private int batchNumber;
    private String description;
    private double sellingUnitPrice;
    private int quantityOnHand;
    private String offerId;

    public ItemTm() {
    }

    public ItemTm(String itemId, int batchNumber, String description, double sellingUnitPrice, int quantityOnHand, String offerId) {
        this.itemId = itemId;
        this.batchNumber = batchNumber;
        this.description = description;
        this.sellingUnitPrice = sellingUnitPrice;
        this.quantityOnHand = quantityOnHand;
        this.offerId = offerId;
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

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }
}
