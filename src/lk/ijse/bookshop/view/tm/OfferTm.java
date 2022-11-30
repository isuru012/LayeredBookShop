package lk.ijse.bookshop.view.tm;

import java.sql.Date;
import java.sql.Time;

public class OfferTm {
    private String itemCode;
    private int batchNumber;
    private double amount;
    private Date startingDate;
    private Date endingDate;

    public OfferTm() {
    }

    public OfferTm(String itemCode, int batchNumber, double amount, Date startingDate, Date endingDate) {
        this.itemCode = itemCode;
        this.batchNumber = batchNumber;
        this.amount = amount;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public int getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }
}
