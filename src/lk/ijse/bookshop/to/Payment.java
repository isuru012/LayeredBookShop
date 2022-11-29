package lk.ijse.bookshop.to;

import java.sql.Date;
import java.sql.Time;

public class Payment {
    private String paymentId;
    private double amount;
    private Date date;
    private Time time;
    private String username;
    private String supplierOrderId;
    private String expenditureId;

    public Payment() {
    }

    public Payment(String paymentId, double amount, Date date, Time time, String username, String supplierOrderId, String expenditureId) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.username = username;
        this.supplierOrderId = supplierOrderId;
        this.expenditureId = expenditureId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSupplierOrderId() {
        return supplierOrderId;
    }

    public void setSupplierOrderId(String supplierOrderId) {
        this.supplierOrderId = supplierOrderId;
    }

    public String getExpenditureId() {
        return expenditureId;
    }

    public void setExpenditureId(String expenditureId) {
        this.expenditureId = expenditureId;
    }
}
