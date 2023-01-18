package lk.ijse.bookshop.dto;

public class ItemDTO {
    private String ItemId;
    private int BatchNumber;
    private String Description;
    private double BuyingUnitPrice;
    private double SellingUnitPrice;
    private int QuantityOnHand;
    private String OfferId;

    public ItemDTO() {
    }

    public ItemDTO(String itemId, int batchNumber, String description, double buyingUnitPrice, double sellingUnitPrice, int quantityOnHand, String offerId) {
        ItemId = itemId;
        BatchNumber = batchNumber;
        Description = description;
        BuyingUnitPrice = buyingUnitPrice;
        SellingUnitPrice = sellingUnitPrice;
        QuantityOnHand = quantityOnHand;
        OfferId = offerId;
    }


    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
    }

    public int getBatchNumber() {
        return BatchNumber;
    }

    public void setBatchNumber(int batchNumber) {
        BatchNumber = batchNumber;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getBuyingUnitPrice() {
        return BuyingUnitPrice;
    }

    public void setBuyingUnitPrice(double buyingUnitPrice) {
        BuyingUnitPrice = buyingUnitPrice;
    }

    public double getSellingUnitPrice() {
        return SellingUnitPrice;
    }

    public void setSellingUnitPrice(double sellingUnitPrice) {
        SellingUnitPrice = sellingUnitPrice;
    }

    public int getQuantityOnHand() {
        return QuantityOnHand;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        QuantityOnHand = quantityOnHand;
    }

    public String getOfferId() {
        return OfferId;
    }

    public void setOfferId(String offerId) {
        OfferId = offerId;
    }
}
