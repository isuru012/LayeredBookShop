package lk.ijse.bookshop.to;

import java.sql.Date;

public class Offer {
    private String offerId;
    private double amount;
    private Date startDate;
    private Date endDate;

    public Offer() {
    }

    public Offer(String offerId, double amount, Date startDate, Date endDate) {
        this.offerId = offerId;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
