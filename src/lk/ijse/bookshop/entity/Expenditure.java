package lk.ijse.bookshop.entity;

import java.sql.Date;
import java.sql.Time;

public class Expenditure {
    private String expenditureId;
    private String description;
    private double amount;
    private Date date;
    private Time time;
    private String userName;

    public Expenditure() {
    }

    public Expenditure(String expenditureId, String description, double amount, Date date, Time time, String userName) {
        this.expenditureId = expenditureId;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.userName = userName;
    }

    public String getExpenditureId() {
        return expenditureId;
    }

    public void setExpenditureId(String expenditureId) {
        this.expenditureId = expenditureId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Expenditure{" +
                "expenditureId='" + expenditureId + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", time=" + time +
                ", userName='" + userName + '\'' +
                '}';
    }
}
