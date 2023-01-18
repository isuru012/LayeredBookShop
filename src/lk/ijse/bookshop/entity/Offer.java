package lk.ijse.bookshop.entity;

import java.sql.Date;
import java.sql.Time;

public class Offer {
    private String offerId;
    private double ammount;
    private Date startedDate;
    private Date endedDate;

    public Offer() {
    }

    public Offer(String offerId, double ammount, Date startedDate, Date endedDate) {
        this.offerId = offerId;
        this.ammount = ammount;
        this.startedDate = startedDate;
        this.endedDate = endedDate;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }

    public Date getEndedDate() {
        return endedDate;
    }

    public void setEndedDate(Date endedDate) {
        this.endedDate = endedDate;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "offerId='" + offerId + '\'' +
                ", ammount=" + ammount +
                ", startedDate=" + startedDate +
                ", endedDate=" + endedDate +
                '}';
    }
}
