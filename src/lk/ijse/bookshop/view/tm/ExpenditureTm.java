package lk.ijse.bookshop.view.tm;

import java.sql.Date;
import java.sql.Time;

public class ExpenditureTm {
    private String expenditure;
    private String description;
    private Time time;
    private Date date;
    private double amount;

    public ExpenditureTm() {
    }

    public ExpenditureTm(String expenditure, String description, Time time, Date date, double amount) {
        this.expenditure = expenditure;
        this.description = description;
        this.time = time;
        this.date = date;
        this.amount = amount;
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
