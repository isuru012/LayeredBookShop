package lk.ijse.bookshop.to;

import java.sql.Date;
import java.sql.Time;

public class Expenditure {
    private String expenditure;
    private String description;
    private double amount;
    private Date date;
    private Time time;
    private String userName;

    public Expenditure() {
    }

    public Expenditure(String expenditure, String description, double amount, Date date, Time time, String userName) {
        this.expenditure = expenditure;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.userName = userName;
    }

    public String getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(String expenditure) {
        this.expenditure = expenditure;
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
}
